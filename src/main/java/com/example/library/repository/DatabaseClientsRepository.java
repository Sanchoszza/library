package com.example.library.repository;

import com.example.library.model.Author;
import com.example.library.model.Clients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DatabaseClientsRepository extends JpaRepository<Clients, Long>, JpaSpecificationExecutor<Clients> {

    @Override
    @EntityGraph(attributePaths = {"books"})
    List<Clients> findAll();

    Page<Clients> findAllByName(String name, Pageable pageable);
}
