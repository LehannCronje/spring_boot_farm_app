package com.example.farmapp.Service;

import java.util.Optional;

import com.example.farmapp.Entity.WorkerGroupFarmEmployee;

public interface WorkerGroupFarmEmployeeService {

	public void insertWorkerGroupFarmEmployee(WorkerGroupFarmEmployee workerGroupFarmEmployee);
	
	public Optional<WorkerGroupFarmEmployee> findWorkerGroupFarmEmployeeById(Long workerGroupFarmEmployeeId);
}
