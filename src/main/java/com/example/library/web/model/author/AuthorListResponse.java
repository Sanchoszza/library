package com.example.library.web.model.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorListResponse {

    private List<AuthorResponse> authors =new ArrayList<>();
}
