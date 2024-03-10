package com.example.library.web.model.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String author;
}
