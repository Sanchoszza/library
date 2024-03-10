package com.example.library.mapper;

import com.example.library.model.Workers;
import com.example.library.web.model.worker.UpsertWorkerRequest;
import com.example.library.web.model.worker.WorkerListResponse;
import com.example.library.web.model.worker.WorkerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkerMapper {

    Workers requestToWorkers(UpsertWorkerRequest request);

    @Mapping(source = "id", target = "id")
    Workers requestToWorkers(Long id, UpsertWorkerRequest request);

    WorkerResponse workerToResponse(Workers workers);

    default WorkerListResponse workerListToWorkerResponseList(List<Workers> workers){
        WorkerListResponse response = new WorkerListResponse();

        response.setWorkers(workers.stream()
                .map(this::workerToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
