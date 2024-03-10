package com.example.library.repository.specification;

import com.example.library.model.Books;
import com.example.library.model.Category;
import com.example.library.web.model.category.CategoryFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface CategorySpecification {

    static Specification<Category> withFilter(CategoryFilter categoryFilter){
        return Specification.where(byCategory(categoryFilter.getCategory()))
                .and(byCreatedAtBefore(categoryFilter.getCratedBefore()))
                .and(byUpdatedBefore(categoryFilter.getUpdatedBefore()));
    }

    static Specification<Category> byCategory(String category){
        return (root, query, criteriaBuilder) -> {
            if (category == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("category"), category);
        };
    }

    static Specification<Category> byCreatedAtBefore(Instant createdBefore){
        return (root, query, criteriaBuilder) -> {
            if (createdBefore == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("createdAt"), createdBefore);
        };
    }

    static Specification<Category> byUpdatedBefore(Instant updatedBefore){
        return (root, query, criteriaBuilder) -> {
            if (updatedBefore == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("updatedAt"), updatedBefore);
        };
    }
}
