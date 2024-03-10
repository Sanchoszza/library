package com.example.library.mapper;

import com.example.library.model.Books;
import com.example.library.service.AuthorService;
import com.example.library.service.CategoryService;
import com.example.library.service.ClientsService;
import com.example.library.web.model.books.UpsertBooksRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BooksMapperDelegate implements BooksMapper{

    @Autowired
    private AuthorService dbAuthorService;

    @Autowired
    private CategoryService dbCategoryService;

    @Autowired
    private ClientsService dbClientsService;

    @Override
    public Books requestToBooks(UpsertBooksRequest request) {
        Books book = new Books();
        book.setAuthor(dbAuthorService.findById(request.getAuthorId()));
        book.setCategory(dbCategoryService.findById(request.getCategoryId()));
        book.setClient(dbClientsService.findById(request.getClientId()));
        book.setTitle(request.getTitle());

        return book;
    }

    @Override
    public Books requestToBooks(Long id, UpsertBooksRequest request) {
        Books book = requestToBooks(request);
        book.setId(id);

        return book;
    }
}
