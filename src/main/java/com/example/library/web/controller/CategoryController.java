package com.example.library.web.controller;

import com.example.library.mapper.CategoryMapper;
import com.example.library.model.Category;
import com.example.library.service.CategoryService;
import com.example.library.web.model.category.CategoryFilter;
import com.example.library.web.model.category.CategoryListResponse;
import com.example.library.web.model.category.CategoryResponse;
import com.example.library.web.model.category.UpsertCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService dbCategoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping("/filter")
    public ResponseEntity<CategoryListResponse> filterBy(CategoryFilter filter){
        return ResponseEntity.ok(categoryMapper
                .categoryResponseToCategoryListResponse(dbCategoryService.filterBy(filter)));
    }

    @GetMapping
    public ResponseEntity<CategoryListResponse> findAll(){
        return ResponseEntity.ok(categoryMapper
                .categoryResponseToCategoryListResponse(dbCategoryService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                categoryMapper.categoryResponse(dbCategoryService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody UpsertCategoryRequest request){
        Category newCategory = dbCategoryService.save(categoryMapper.requestToCategory(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.categoryResponse(newCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,
                                                   @RequestBody UpsertCategoryRequest request){
        Category updateCategory = dbCategoryService.save(categoryMapper.requestToCategory(id, request));

        return ResponseEntity.ok(categoryMapper.categoryResponse(updateCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dbCategoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
