package com.example.farmapp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.farmapp.Entity.WorkerGroupFarmEmployee;
import com.example.farmapp.Repository.WorkerGroupFarmEmployeeRepository;

@Service
public class WorkerGroupFarmEmployeeServiceImpl implements WorkerGroupFarmEmployeeService{

	@Autowired
	private WorkerGroupFarmEmployeeRepository workerGroupFarmEmpRepo;
	
	@Override
	public void insertWorkerGroupFarmEmployee(WorkerGroupFarmEmployee workerGroupFarmEmployee) {
		
		workerGroupFarmEmpRepo.save(workerGroupFarmEmployee);
		
	}

	@Override
	public Optional<WorkerGroupFarmEmployee> findWorkerGroupFarmEmployeeById(Long workerGroupFarmEmployeeId) {
		
		return workerGroupFarmEmpRepo.findById(workerGroupFarmEmployeeId);
		
	}
	

}
