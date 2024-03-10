package com.example.library.web.model.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertClientsRequest {

    @NotBlank(message = "Name have to be field in")
    @Size(min = 2, max = 30, message = "Client's name cant be less {min} and more {max}")
    private String name;

    private Long bookId;
}
