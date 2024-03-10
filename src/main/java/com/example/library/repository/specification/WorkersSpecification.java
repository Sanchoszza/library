package com.example.library.repository.specification;

import com.example.library.model.Workers;
import com.example.library.web.model.worker.WorkersFilter;
import org.springframework.data.jpa.domain.Specification;

public interface WorkersSpecification {

    static Specification<Workers> withFilter(WorkersFilter workersFilter){
        return Specification.where(byWorker(workersFilter.getWorker()));
    }

    static Specification<Workers> byWorker(String worker){
        return (root, query, criteriaBuilder) -> {
            if (worker == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("workerName"), worker);
        };
    }
}
