package com.example.library.mapper;

import com.example.library.model.Books;
import com.example.library.web.model.books.BooksListResponse;
import com.example.library.web.model.books.BooksResponse;
import com.example.library.web.model.books.UpsertBooksRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(BooksMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BooksMapper {

    Books requestToBooks(UpsertBooksRequest request);

    @Mapping(source = "id", target = "id")
    Books requestToBooks(Long id, UpsertBooksRequest request);

    BooksResponse bookToResponse(Books book);

    List<BooksResponse> bookListToBookResponseList(List<Books> books);

    default BooksListResponse bookListToBookListResponse(List<Books> books){
        BooksListResponse response = new BooksListResponse();
        response.setBooks(bookListToBookResponseList(books));

        return response;
    }
}
