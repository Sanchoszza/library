package com.example.library.web.model.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertBooksRequest {

    private String title;

    private Long authorId;

    private Long categoryId;

    private Long clientId;
}
