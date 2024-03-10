package com.example.library.web.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientsFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String client;
}
