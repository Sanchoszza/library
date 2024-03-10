package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Books;
import com.example.library.web.model.author.AuthorFilter;

import java.util.List;

public interface AuthorService {

    List<Author> filterBy(AuthorFilter filter);

    List<Author> findAll();

    Author findById(Long id);

    Author save(Author author);

    Author update(Author author);

    void delete(Long id);
}
