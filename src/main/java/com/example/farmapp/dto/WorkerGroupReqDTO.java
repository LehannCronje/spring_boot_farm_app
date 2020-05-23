package com.example.farmapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class WorkerGroupReqDTO {

	private List<FarmEmployeeReqDTO> farmEmployees;

	private String workerGroupName;

	private Long farmId;
}
