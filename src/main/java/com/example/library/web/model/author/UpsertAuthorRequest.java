package com.example.library.web.model.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertAuthorRequest {

    @NotBlank(message = "Name have to be field in")
    @Size(min = 2, max = 30, message = "Author's name cant be less {min} and more {max}")
    private String name;

    private Long bookId;
}
