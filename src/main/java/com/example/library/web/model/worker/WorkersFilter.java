package com.example.library.web.model.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkersFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String worker;
}
