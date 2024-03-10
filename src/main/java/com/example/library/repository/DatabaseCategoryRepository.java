package com.example.library.repository;

import com.example.library.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DatabaseCategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    Page<Category> findAllByCategory(String category, Pageable pageable);
}
