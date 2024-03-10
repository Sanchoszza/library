package com.example.library.web.model.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String category;

    private Instant cratedBefore;

    private Instant updatedBefore;
}
