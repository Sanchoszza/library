package com.example.library.service;

import com.example.library.model.Workers;
import com.example.library.web.model.worker.WorkersFilter;

import java.util.List;

public interface WorkersService {

    List<Workers> filterBy(WorkersFilter filter);

    List<Workers> findAll();

    Workers findById(Long id);

    Workers save(Workers workers);

    Workers update(Workers workers);

    void delete(Long id);
}
