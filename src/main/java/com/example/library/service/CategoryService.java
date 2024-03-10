package com.example.library.service;

import com.example.library.model.Category;
import com.example.library.web.model.category.CategoryFilter;

import java.util.List;

public interface CategoryService {

    List<Category> filterBy(CategoryFilter filter);

    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    Category update(Category category);

    void delete(Long id);
}
