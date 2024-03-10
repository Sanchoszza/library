package com.example.library.mapper;

import com.example.library.model.Clients;
import com.example.library.web.model.client.ClientsListResponse;
import com.example.library.web.model.client.ClientsResponse;
import com.example.library.web.model.client.UpsertClientsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientsMapper {

    Clients requestToClients(UpsertClientsRequest request);

    @Mapping(source = "id", target = "id")
    Clients requestToClients(Long id, UpsertClientsRequest request);

    ClientsResponse clientToResponse(Clients clients);

    default ClientsListResponse clientsListToClientsResponseList(List<Clients> clients){
        ClientsListResponse response = new ClientsListResponse();

        response.setClients(clients.stream()
                .map(this::clientToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
