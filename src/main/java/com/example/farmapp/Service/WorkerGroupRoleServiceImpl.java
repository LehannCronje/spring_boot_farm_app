package com.example.farmapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.farmapp.Entity.WorkerGroupFarmEmployee;
import com.example.farmapp.Entity.WorkerGroupRole;
import com.example.farmapp.Repository.WorkerGroupRoleRepository;
import com.example.farmapp.dto.WorkerGroupFarmEmployeeResDTO;
import com.example.farmapp.dto.WorkerGroupRoleResDTO;

@Service
public class WorkerGroupRoleServiceImpl implements WorkerGroupRoleService {

	@Autowired
	private WorkerGroupRoleRepository workerGroupRoleRepo;

	@Override
	public void insertWorkerGroupRole(WorkerGroupRole workerGroupRole) {
		workerGroupRoleRepo.save(workerGroupRole);
	}

	@Override
	public List<WorkerGroupRole> getWorkerGroupRolesById(Long workerGroupId) {

		return workerGroupRoleRepo.findByWorkerGroupId(workerGroupId);

	}

	@Override
	public Optional<WorkerGroupRole> findWorkerGroupRoleById(Long workerGroupId) {
		return workerGroupRoleRepo.findById(workerGroupId);
	}

	// @Override
	// public List<WorkerGroupRoleResDTO> getWorkerGroupRoleResDTOsDById(Long
	// workerGroupId) {
	// return
	// workerGroupRoleRepo.findByWorkerGroupId(workerGroupId).stream().map(workerGroupRole
	// -> {
	// WorkerGroupRoleResDTO workerGroupRoleResDTO = new WorkerGroupRoleResDTO();
	// workerGroupRoleResDTO.setName(workerGroupRole.getEmployeeRole().getName());
	// worker

	// }).collect(Collectors.toList());
	// }

}
