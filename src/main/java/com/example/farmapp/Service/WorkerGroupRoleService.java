package com.example.farmapp.Service;

import java.util.List;
import java.util.Optional;

import com.example.farmapp.Entity.WorkerGroupRole;
import com.example.farmapp.dto.WorkerGroupRoleResDTO;

public interface WorkerGroupRoleService {

	public void insertWorkerGroupRole(WorkerGroupRole workerGroupRole);

	public Optional<WorkerGroupRole> findWorkerGroupRoleById(Long workerGroupId);

	public List<WorkerGroupRole> getWorkerGroupRolesById(Long workerGroupId);

	// public List<WorkerGroupRoleResDTO> getWorkerGroupRoleResDTOsDById(Long
	// workerGroupId);
}
