package com.example.library.repository.specification;

import com.example.library.model.Books;
import com.example.library.web.model.books.BooksFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface BooksSpecification {

    static Specification<Books> withFilter(BooksFilter booksFilter){
        return Specification.where(byBook(booksFilter.getBook()))
                .and(byAuthorId(booksFilter.getAuthorId()))
                .and(byCategoryId(booksFilter.getCategoryId()))
                .and(byCreatedAtBefore(booksFilter.getCreatedBefore()))
                .and(byUpdatedBefore(booksFilter.getUpdatedBefore()));
    }

    static Specification<Books> byBook(String book){
        return (root, query, criteriaBuilder) -> {
            if (book == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("title"), book);
        };
    }

    static Specification<Books> byAuthorId(Long authorId){
        return (root, query, criteriaBuilder) -> {
            if (authorId == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("authorId"), authorId);
        };
    }

    static Specification<Books> byCategoryId(Long categoryId){
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("categoryId"), categoryId);
        };
    }

    static Specification<Books> byCreatedAtBefore(Instant createdBefore){
        return (root, query, criteriaBuilder) -> {
            if (createdBefore == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("createdAt"), createdBefore);
        };
    }

    static Specification<Books> byUpdatedBefore(Instant updatedBefore){
        return (root, query, criteriaBuilder) -> {
            if (updatedBefore == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("updatedAt"), updatedBefore);
        };
    }
}
