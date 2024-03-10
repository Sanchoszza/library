package com.example.library.service.impl;

import com.example.library.exception.EntityNotFoundException;
import com.example.library.model.Author;
import com.example.library.model.Books;
import com.example.library.model.Category;
import com.example.library.model.Clients;
import com.example.library.repository.DatabaseAuthorRepository;
import com.example.library.repository.DatabaseBooksRepository;
import com.example.library.repository.DatabaseCategoryRepository;
import com.example.library.repository.DatabaseClientsRepository;
import com.example.library.repository.specification.BooksSpecification;
import com.example.library.service.AuthorService;
import com.example.library.service.BooksService;
import com.example.library.service.CategoryService;
import com.example.library.service.ClientsService;
import com.example.library.utils.BeanUtils;
import com.example.library.web.model.books.BooksFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BooksService {

    private final DatabaseBooksRepository booksRepository;

    private final DatabaseAuthorRepository authorRepository;

    private final DatabaseCategoryRepository categoryRepository;

    private final DatabaseClientsRepository clientsRepository;

    private final AuthorService authorService;

    private final CategoryService categoryService;

    private final ClientsService clientsService;

    @Override
    public List<Books> filterBy(BooksFilter filter) {
        return booksRepository.findAll(BooksSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public List<Books> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Books findById(Long id) {
        return booksRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Book with ID {0} not found", id
                )));
    }

    @Override
    public Books save(Books books) {
        Author author = authorService.findById(books.getAuthor().getId());
        Category category = categoryService.findById(books.getCategory().getId());
        Clients clients = clientsService.findById(books.getClient().getId());
        books.setAuthor(author);
        books.setCategory(category);
        books.setClient(clients);
        return booksRepository.save(books);
    }

    @Override
    public Books update(Books books) {
        checkForUpdate(books.getId());
        Books existedBook = findById(books.getId());

        BeanUtils.copyNonNullProperties(books, existedBook);
        return booksRepository.save(existedBook);
    }

    @Override
    public void delete(Long id) {
        booksRepository.deleteById(id);
    }
}
