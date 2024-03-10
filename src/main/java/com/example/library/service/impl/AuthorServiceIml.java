package com.example.library.service.impl;

import com.example.library.exception.EntityNotFoundException;
import com.example.library.model.Author;
import com.example.library.repository.DatabaseAuthorRepository;
import com.example.library.repository.specification.AuthorSpecification;
import com.example.library.service.AuthorService;
import com.example.library.utils.BeanUtils;
import com.example.library.web.model.author.AuthorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceIml implements AuthorService {

    private final DatabaseAuthorRepository authorRepository;

    @Override
    public List<Author> filterBy(AuthorFilter filter) {
        return authorRepository.findAll(AuthorSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format(
                        "Author with ID {0} not found", id
                )));
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author author) {
        Author existedAuthor = findById(author.getId());
        BeanUtils.copyNonNullProperties(author, existedAuthor);
        return authorRepository.save(existedAuthor);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
