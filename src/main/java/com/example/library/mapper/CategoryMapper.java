package com.example.library.mapper;

import com.example.library.model.Category;
import com.example.library.web.model.category.CategoryListResponse;
import com.example.library.web.model.category.CategoryResponse;
import com.example.library.web.model.category.UpsertCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category requestToCategory(UpsertCategoryRequest request);

    @Mapping(source = "id", target = "id")
    Category requestToCategory(Long id, UpsertCategoryRequest request);

    CategoryResponse categoryResponse(Category category);

    default CategoryListResponse categoryResponseToCategoryListResponse(List<Category> categories){
        CategoryListResponse response = new CategoryListResponse();

        response.setCategories(categories.stream()
                .map(this::categoryResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
