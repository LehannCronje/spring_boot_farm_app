package com.example.farmapp.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class WorkerGroupRoleResDTO {

	private Long id;

	private String name;

	List<WorkerGroupFarmEmployeeResDTO> workerGroupfarmEmployeesResDTO = new ArrayList<WorkerGroupFarmEmployeeResDTO>();

}
