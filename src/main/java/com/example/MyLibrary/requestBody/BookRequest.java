package com.example.MyLibrary.requestBody;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

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

    @NotBlank(message = "Publish date is required.")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Publish date must be in format dd/MM/yyyy.")
    private String publishDate;
}

