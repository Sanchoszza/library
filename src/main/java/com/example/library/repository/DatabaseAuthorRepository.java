package com.example.library.repository;

import com.example.library.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DatabaseAuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    @Override
    @EntityGraph(attributePaths = {"books"})
    List<Author> findAll();

    Page<Author> findAllByName(String name, Pageable pageable);
}
