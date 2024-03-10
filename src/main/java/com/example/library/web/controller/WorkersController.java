package com.example.library.web.controller;

import com.example.library.mapper.WorkerMapper;
import com.example.library.model.Workers;
import com.example.library.service.WorkersService;
import com.example.library.web.model.worker.UpsertWorkerRequest;
import com.example.library.web.model.worker.WorkerListResponse;
import com.example.library.web.model.worker.WorkerResponse;
import com.example.library.web.model.worker.WorkersFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/workers")
@RequiredArgsConstructor
public class WorkersController {

    private final WorkersService dbWorkersService;

    private final WorkerMapper workerMapper;

    @GetMapping("/filter")
    public ResponseEntity<WorkerListResponse> filterBy(WorkersFilter filter){
        return ResponseEntity.ok(workerMapper.workerListToWorkerResponseList(
                dbWorkersService.filterBy(filter)
        ));
    }

    @GetMapping
    public ResponseEntity<WorkerListResponse> findAll(){
        return ResponseEntity.ok(workerMapper.workerListToWorkerResponseList(
                dbWorkersService.findAll()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(workerMapper.workerToResponse(
                dbWorkersService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<WorkerResponse> create(@RequestBody UpsertWorkerRequest request){
        Workers nweWorker = dbWorkersService.save(workerMapper.requestToWorkers(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(workerMapper.workerToResponse(nweWorker));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkerResponse> update(@PathVariable("id") Long id,
                                                 @RequestBody UpsertWorkerRequest request){
        Workers updateWorker = dbWorkersService.update(workerMapper.requestToWorkers(id, request));

        return ResponseEntity.ok(workerMapper.workerToResponse(updateWorker));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dbWorkersService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
