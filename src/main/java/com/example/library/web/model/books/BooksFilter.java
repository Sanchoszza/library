package com.example.library.web.model.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String book;

    private Instant createdBefore;

    private Instant updatedBefore;

    private Long authorId;

    private Long categoryId;
}
