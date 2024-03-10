package com.example.library.service.impl;

import com.example.library.exception.EntityNotFoundException;
import com.example.library.model.Books;
import com.example.library.model.Clients;
import com.example.library.repository.DatabaseClientsRepository;
import com.example.library.repository.specification.CategorySpecification;
import com.example.library.repository.specification.ClientsSpecification;
import com.example.library.service.ClientsService;
import com.example.library.utils.BeanUtils;
import com.example.library.web.model.client.ClientsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientsServiceImpl implements ClientsService {

    private final DatabaseClientsRepository clientsRepository;

    @Override
    public List<Clients> filterBy(ClientsFilter filter) {
        return clientsRepository.findAll(ClientsSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public List<Clients> findAll() {
        return clientsRepository.findAll();
    }

    @Override
    public Clients findById(Long id) {
        return clientsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Client with ID {0} not found", id
                )));
    }

    @Override
    public Clients save(Clients clients) {
        return clientsRepository.save(clients);
    }

    @Override
    public Clients update(Clients clients) {
        Clients existedClient = findById(clients.getId());
        BeanUtils.copyNonNullProperties(clients, existedClient);
        return clientsRepository.save(existedClient);
    }

    @Override
    public void delete(Long id) {
        clientsRepository.deleteById(id);
    }

}
