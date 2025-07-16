package com.example.MyLibrary.controllers;

import com.example.MyLibrary.models.Book;
import com.example.MyLibrary.requestBody.BookRequest;
import com.example.MyLibrary.services.BookService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with ID " + id + " not found.");
        }
        return ResponseEntity.ok(book);
    }


    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody @Valid BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setSummary(request.getSummary());

        bookService.addBook(book, request.getPublishDate());  // publishDate = "dd/MM/yyyy"
        return ResponseEntity.ok("Book added successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        boolean deleted = bookService.deleteBook(id);
        return deleted ? ResponseEntity.ok("Book deleted.") : ResponseEntity.notFound().build();
    }


}
