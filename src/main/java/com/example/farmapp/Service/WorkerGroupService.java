package com.example.farmapp.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.farmapp.Entity.WorkerGroup;
import com.example.farmapp.dto.EmployeeResDTO;
import com.example.farmapp.dto.WorkerGroupFarmEmployeeReqDTO;
import com.example.farmapp.dto.WorkerGroupReqDTO;
import com.example.farmapp.dto.WorkerGroupResDTO;

public interface WorkerGroupService {

	public void createWorkerGroup(WorkerGroupReqDTO workerGroupReqDto);

	public Set<WorkerGroupResDTO> getWorkerGroups(Long farmId);

	public List<EmployeeResDTO> getEmployeeByWorkerGroupId(Long workerGroupId);

	public void addWorkerGroupEmployee(WorkerGroupFarmEmployeeReqDTO workerGroupFarmEmployeeReqDTO);

	public WorkerGroupResDTO findWorkerGroupByFarmEmpId(Long farmEmpId);

	public List<EmployeeResDTO> getEmployeeByWorkerGroupIdFilterByMembers(Long workerGroupId);
}
