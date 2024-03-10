package com.example.library.service.impl;

import com.example.library.exception.EntityNotFoundException;
import com.example.library.model.Category;
import com.example.library.repository.DatabaseCategoryRepository;
import com.example.library.repository.specification.CategorySpecification;
import com.example.library.service.CategoryService;
import com.example.library.utils.BeanUtils;
import com.example.library.web.model.category.CategoryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final DatabaseCategoryRepository categoryRepository;

    @Override
    public List<Category> filterBy(CategoryFilter filter) {
        return categoryRepository.findAll(CategorySpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format(
                        "Category with ID {0} not found", id
                )));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category existedCategory = findById(category.getId());
        BeanUtils.copyNonNullProperties(category, existedCategory);
        return categoryRepository.save(existedCategory);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
