package com.example.library.repository.specification;

import com.example.library.model.Clients;
import com.example.library.web.model.client.ClientsFilter;
import org.springframework.data.jpa.domain.Specification;

public interface ClientsSpecification {

    static Specification<Clients> withFilter(ClientsFilter clientsFilter){
        return Specification.where(byClient(clientsFilter.getClient()));
    }

    static Specification<Clients> byClient(String client){
        return (root, query, criteriaBuilder) -> {
            if (client == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("clientName"), client);
        };
    }


}
