package com.example.library.repository;

import com.example.library.model.Workers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DatabaseWorkersRepository extends JpaRepository<Workers, Long>, JpaSpecificationExecutor<Workers> {

    Page<Workers> findAllByName(String name, Pageable pageable);
}
