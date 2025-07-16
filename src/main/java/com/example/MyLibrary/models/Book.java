package com.example.MyLibrary.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private String title;
    private String author;
    private String summary;

    // Stored as dd-MMM-yyyy (e.g. 10-Jul-2025)
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate publishDate;
}
