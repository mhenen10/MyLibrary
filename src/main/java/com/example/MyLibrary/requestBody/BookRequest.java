package com.example.MyLibrary.requestBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequest {

    @NotBlank(message = "Title is required.")
    @Size(max = 100, message = "Title must not exceed 100 characters.")
    private String title;

    @NotBlank(message = "Author is required.")
    @Size(max = 60, message = "Author name must not exceed 60 characters.")
    private String author;

    @NotBlank(message = "Summary is required.")
    @Size(max = 1000, message = "Summary must not exceed 1000 characters.")
    private String summary;

    @NotNull(message = "Publish date is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate publishDate;
}

