package com.example.library.web.controller;

import com.example.library.mapper.AuthorMapper;
import com.example.library.model.Author;
import com.example.library.model.Books;
import com.example.library.service.AuthorService;
import com.example.library.web.model.author.*;
import com.example.library.web.model.books.UpsertBooksRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService dbAuthorService;

    private final AuthorMapper authorMapper;

    @GetMapping("/filter")
    public ResponseEntity<AuthorListResponse> filterBy(AuthorFilter filter){
        return ResponseEntity.ok(
                authorMapper.authorListToAuthorResponseList(
                        dbAuthorService.filterBy(filter)
                )
        );
    }

    @GetMapping
    public ResponseEntity<AuthorListResponse> findAll(){
        return ResponseEntity.ok(authorMapper.authorListToAuthorResponseList(
                dbAuthorService.findAll()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(authorMapper.authorToResponse(
                dbAuthorService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> create(@RequestBody UpsertAuthorRequest request){
        Author newAuthor = dbAuthorService.save(authorMapper.requestToAuthor(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorMapper.authorToResponse(newAuthor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable("id") Long authorId,
                                                 @RequestBody UpsertAuthorRequest request){
        Author updateAuthor = dbAuthorService.update(authorMapper.requestToAuthor(authorId, request));

        return ResponseEntity.ok(authorMapper.authorToResponse(updateAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dbAuthorService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
