package com.example.farmapp.Service;

import java.util.List;

import com.example.farmapp.Entity.WorkerGroupRole;
import com.example.farmapp.dto.WorkerGroupRoleResDTO;

public interface WorkerGroupRoleService {

	public void insertWorkerGroupRole(WorkerGroupRole workerGroupRole);
	
	public List<WorkerGroupRole> getWorkerGroupRoleByWorkerGroupId(Long workerGroupId);
}
