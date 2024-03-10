package com.example.library.mapper;

import com.example.library.model.Author;
import com.example.library.web.model.author.AuthorListResponse;
import com.example.library.web.model.author.AuthorResponse;
import com.example.library.web.model.author.UpsertAuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {

    Author requestToAuthor(UpsertAuthorRequest request);

    @Mapping(source = "authorId", target = "id")
    Author requestToAuthor(Long authorId, UpsertAuthorRequest request);

    AuthorResponse authorToResponse(Author author);

    default AuthorListResponse authorListToAuthorResponseList(List<Author> authors){
        AuthorListResponse response = new AuthorListResponse();

        response.setAuthors(authors.stream()
                .map(this::authorToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
