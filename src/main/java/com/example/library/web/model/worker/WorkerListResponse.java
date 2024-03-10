package com.example.library.web.model.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerListResponse {

    private List<WorkerResponse> workers = new ArrayList<>();
}
