package com.example.library.web.controller;

import com.example.library.mapper.BooksMapper;
import com.example.library.model.Books;
import com.example.library.service.BooksService;
import com.example.library.web.model.books.BooksFilter;
import com.example.library.web.model.books.BooksListResponse;
import com.example.library.web.model.books.BooksResponse;
import com.example.library.web.model.books.UpsertBooksRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BooksController {

    private final BooksService dbBooksService;

    private final BooksMapper booksMapper;

    @GetMapping("/filter")
    public ResponseEntity<BooksListResponse> filterBy(BooksFilter filter){
        return ResponseEntity.ok(
                booksMapper.bookListToBookListResponse(
                        dbBooksService.filterBy(filter)
                )
        );
    }

    @GetMapping
    public ResponseEntity<BooksListResponse> findAll(){
        return ResponseEntity.ok(booksMapper
                .bookListToBookListResponse(dbBooksService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                booksMapper.bookToResponse(dbBooksService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<BooksResponse> create(@RequestBody UpsertBooksRequest request){
        Books newBook = dbBooksService.save(booksMapper.requestToBooks(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(booksMapper.bookToResponse(newBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BooksResponse> update(@PathVariable("id") Long bookId,
                                                @RequestBody UpsertBooksRequest request){
        Books updateBook = dbBooksService.update(booksMapper.requestToBooks(bookId, request));

        return ResponseEntity.ok(booksMapper.bookToResponse(updateBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dbBooksService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
