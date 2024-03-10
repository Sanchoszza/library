package com.example.library.web.controller;

import com.example.library.mapper.ClientsMapper;
import com.example.library.model.Clients;
import com.example.library.service.ClientsService;
import com.example.library.web.model.client.ClientsFilter;
import com.example.library.web.model.client.ClientsListResponse;
import com.example.library.web.model.client.ClientsResponse;
import com.example.library.web.model.client.UpsertClientsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientsController {

    private final ClientsService dbClientsService;

    private final ClientsMapper clientsMapper;

    @GetMapping("/filter")
    public ResponseEntity<ClientsListResponse> filterBy(ClientsFilter filter){
        return ResponseEntity.ok(
                clientsMapper.clientsListToClientsResponseList(
                        dbClientsService.filterBy(filter)
                )
        );
    }

    @GetMapping
    public ResponseEntity<ClientsListResponse> findAll(){
        return ResponseEntity.ok(clientsMapper.clientsListToClientsResponseList(
                dbClientsService.findAll()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientsResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(clientsMapper.clientToResponse(
                dbClientsService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<ClientsResponse> create(@RequestBody UpsertClientsRequest request){
        Clients newClient = dbClientsService.save(clientsMapper.requestToClients(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientsMapper.clientToResponse(newClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientsResponse> update(@PathVariable("id") Long clientId,
                                                  @RequestBody UpsertClientsRequest request){
        Clients updateClient = dbClientsService.update(clientsMapper.requestToClients(clientId, request));

        return  ResponseEntity.ok(clientsMapper.clientToResponse(updateClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dbClientsService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
