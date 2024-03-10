package com.example.library.repository;

import com.example.library.model.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DatabaseBooksRepository extends JpaRepository<Books, Long>, JpaSpecificationExecutor<Books> {

    Page<Books> findAllByTitle(String title, Pageable pageable);
}
