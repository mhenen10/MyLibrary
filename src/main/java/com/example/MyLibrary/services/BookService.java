package com.example.MyLibrary.services;

import com.example.MyLibrary.models.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookService {

    private final File file = new File("books.json");
    private final ObjectMapper objectMapper = CustomObjectMapper.create(); // ✅ using your custom one
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        loadBooks();
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public void addBook(Book book, String rawDate) {
        LocalDate publishDate = LocalDate.parse(rawDate, inputFormatter);
        book.setPublishDate(LocalDate.parse(publishDate.format(saveFormatter), saveFormatter));

        book.setId(generateNextId());
        books.add(book);
        saveBooks();
    }

    public boolean deleteBook(int id) {
        boolean removed = books.removeIf(book -> book.getId() == id);
        if (removed) saveBooks();
        return removed;
    }

    private int generateNextId() {
        return books.stream().mapToInt(Book::getId).max().orElse(0) + 1;
    }

    private void loadBooks() {
        try {
            if (file.exists()) {
                books = objectMapper.readValue(file, new TypeReference<List<Book>>() {});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveBooks() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
