package com.example.library.service.impl;

import com.example.library.exception.EntityNotFoundException;
import com.example.library.model.Workers;
import com.example.library.repository.DatabaseWorkersRepository;
import com.example.library.repository.specification.WorkersSpecification;
import com.example.library.service.WorkersService;
import com.example.library.utils.BeanUtils;
import com.example.library.web.model.worker.WorkersFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkersServiceImpl implements WorkersService {

    private final DatabaseWorkersRepository workersRepository;

    @Override
    public List<Workers> filterBy(WorkersFilter filter) {
        return workersRepository.findAll(WorkersSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public List<Workers> findAll() {
        return workersRepository.findAll();
    }

    @Override
    public Workers findById(Long id) {
        return workersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Worker with ID {0} not found", id
                )));
    }

    @Override
    public Workers save(Workers workers) {
        return workersRepository.save(workers);
    }

    @Override
    public Workers update(Workers workers) {
        Workers existedWorker = findById(workers.getId());
        BeanUtils.copyNonNullProperties(workers, existedWorker);
        return workersRepository.save(existedWorker);
    }

    @Override
    public void delete(Long id) {
        workersRepository.deleteById(id);
    }
}
