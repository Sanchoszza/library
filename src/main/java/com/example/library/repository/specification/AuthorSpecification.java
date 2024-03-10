package com.example.library.repository.specification;

import com.example.library.model.Author;
import com.example.library.web.model.author.AuthorFilter;
import org.springframework.data.jpa.domain.Specification;

public interface AuthorSpecification {

    static Specification<Author> withFilter(AuthorFilter authorFilter){
        return Specification.where(byAuthor(authorFilter.getAuthor()));
    }

    static Specification<Author> byAuthor(String author){
        return (root, query, criteriaBuilder) ->{
            if(author == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("name"), author);
        };
    }
}
