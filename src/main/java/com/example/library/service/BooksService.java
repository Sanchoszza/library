package com.example.library.service;

import com.example.library.exception.UpdateStateException;
import com.example.library.model.Books;
import com.example.library.web.model.books.BooksFilter;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public interface BooksService {

    List<Books> filterBy(BooksFilter filter);

    List<Books> findAll();

    Books findById(Long id);

    Books save(Books books);

    Books update(Books books);

    void delete(Long id);

    default void checkForUpdate(Long bookId){
        Books currentBook = findById(bookId);
        Instant now = Instant.now();

        Duration duration = Duration.between(currentBook.getUpdateAt(), now);

        if (duration.getSeconds() > 1000){
            throw new UpdateStateException("Impossible to update book");
        }
    }
}
