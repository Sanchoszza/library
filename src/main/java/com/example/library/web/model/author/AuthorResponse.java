package com.example.library.web.model.author;

import com.example.library.web.model.books.BooksResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

    private Long id;
    private String name;
    private List<BooksResponse> books = new ArrayList<>();
}
