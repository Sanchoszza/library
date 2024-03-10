package com.example.library.service;

import com.example.library.model.Books;
import com.example.library.model.Clients;
import com.example.library.web.model.client.ClientsFilter;

import java.util.List;

public interface ClientsService {

    List<Clients> filterBy(ClientsFilter filter);

    List<Clients> findAll();

    Clients findById(Long id);

    Clients save(Clients clients);

    Clients update(Clients clients);

    void  delete(Long id);

}
