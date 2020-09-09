package com.example.farmapp.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import com.example.farmapp.Entity.Employee;
import com.example.farmapp.Entity.EmployeeRole;
import com.example.farmapp.Entity.Farm;
import com.example.farmapp.Entity.FarmEmployee;
import com.example.farmapp.Entity.WorkerGroup;
import com.example.farmapp.Entity.WorkerGroupFarmEmployee;
import com.example.farmapp.Entity.WorkerGroupRole;
import com.example.farmapp.Repository.WorkerGroupRepository;
import com.example.farmapp.dto.EmployeeResDTO;
import com.example.farmapp.dto.FarmEmployeeReqDTO;
import com.example.farmapp.dto.WorkerGroupFarmEmployeeReqDTO;
import com.example.farmapp.dto.WorkerGroupReqDTO;
import com.example.farmapp.dto.WorkerGroupResDTO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerGroupServiceImpl implements WorkerGroupService {

	@Autowired
	private FarmService farmService;

	@Autowired
	private WorkerGroupRepository workerGroupRepo;

	@Autowired
	private EmployeeRoleService employeeRoleService;

	@Autowired
	private WorkerGroupRoleService workerGroupRoleService;

	@Autowired
	private FarmEmployeeService farmEmployeeService;

	@Autowired
	private WorkerGroupFarmEmployeeService workerGroupFarmEmployeeService;

	@Override
	public void createWorkerGroup(WorkerGroupReqDTO workerGroupReqDto) {

		WorkerGroup workerGroup = new WorkerGroup();
		Farm farm = farmService.findFarmById(workerGroupReqDto.getFarmId()).get();
		FarmEmployee farmEmployee = new FarmEmployee();
		WorkerGroupFarmEmployee workerGroupFarmEmployee = new WorkerGroupFarmEmployee();

		workerGroup.setFarm(farm);
		workerGroup.setName(workerGroupReqDto.getWorkerGroupName());
		this.insertWorkerGroup(workerGroup);

		WorkerGroupRole workerGroupMember = new WorkerGroupRole();
		WorkerGroupRole workerGroupLeader = new WorkerGroupRole();

		workerGroupMember.setEmployeeRole(employeeRoleService.findByName("MEMBER").get());
		workerGroupMember.setWorkerGroup(workerGroup);
		workerGroupRoleService.insertWorkerGroupRole(workerGroupMember);
		workerGroupLeader.setEmployeeRole(employeeRoleService.findByName("LEADER").get());
		workerGroupLeader.setWorkerGroup(workerGroup);
		workerGroupRoleService.insertWorkerGroupRole(workerGroupLeader);

		for (FarmEmployeeReqDTO farmEmployeeReqDto : workerGroupReqDto.getFarmEmployees()) {
			farmEmployee = farmEmployeeService.findFarmEmployeeById(farmEmployeeReqDto.getId()).get();
			workerGroupFarmEmployee = new WorkerGroupFarmEmployee();
			if (farmEmployeeReqDto.getRole().equals("LEADER")) {
				workerGroupFarmEmployee.setFarmEmployee(farmEmployee);
				workerGroupFarmEmployee.setWorkerGroupRole(workerGroupLeader);
				workerGroupFarmEmployeeService.insertWorkerGroupFarmEmployee(workerGroupFarmEmployee);
			}
			if (farmEmployeeReqDto.getRole().equals("MEMBER")) {
				workerGroupFarmEmployee.setFarmEmployee(farmEmployee);
				workerGroupFarmEmployee.setWorkerGroupRole(workerGroupMember);
				workerGroupFarmEmployeeService.insertWorkerGroupFarmEmployee(workerGroupFarmEmployee);
			}
		}
	}

	public void insertWorkerGroup(WorkerGroup workerGroup) {
		workerGroupRepo.save(workerGroup);
	}

	@Override
	public Set<WorkerGroupResDTO> getWorkerGroups(Long farmId) {
		Farm farm = farmService.findFarmById(farmId).get();
		Set<WorkerGroupResDTO> workerGroupSet = new HashSet<WorkerGroupResDTO>();
		WorkerGroupResDTO workerGroupResDto = new WorkerGroupResDTO();
		for (WorkerGroup workerGroup : farm.getWorkerGroup()) {
			workerGroupResDto = new WorkerGroupResDTO();
			workerGroupResDto.setId(workerGroup.getId());
			workerGroupResDto.setName(workerGroup.getName());
			workerGroupSet.add(workerGroupResDto);
		}
		return workerGroupSet;
	}

	@Override
	public List<EmployeeResDTO> getEmployeeByWorkerGroupId(Long workerGroupId) {

		List<EmployeeResDTO> farmEmployees = new ArrayList<EmployeeResDTO>();
		Employee employee = new Employee();
		EmployeeResDTO empResDTO = new EmployeeResDTO();

		for (WorkerGroupRole workerGroupRole : workerGroupRoleService.getWorkerGroupRolesById(workerGroupId)) {
			for (WorkerGroupFarmEmployee workerGroupFarmEmployee : workerGroupRole.getWorkerGroupFarmEmployees()) {
				employee = workerGroupFarmEmployee.getFarmEmployee().getEmployee();
				empResDTO = new EmployeeResDTO();
				empResDTO.setId(workerGroupFarmEmployee.getFarmEmployee().getId());
				empResDTO.setName(employee.getName());
				try {
					if (employee.getUser() == null) {
						empResDTO.setUserActive("false");
					} else if (employee.getUser().isEnabled()) {
						empResDTO.setUserActive("true");
					} else {
						empResDTO.setUserActive("disabled");
					}
				} catch (EntityNotFoundException e) {
					empResDTO.setUserActive("disabled");
				}

			}
			empResDTO.setRole(workerGroupRole.getEmployeeRole().getName());
			farmEmployees.add(empResDTO);
		}
		return farmEmployees;
	}

	@Override
	public void addWorkerGroupEmployee(WorkerGroupFarmEmployeeReqDTO workerGroupFarmEmployeeReqDTO) {

		WorkerGroupFarmEmployee workerGroupFarmEmployee = new WorkerGroupFarmEmployee();
		WorkerGroupRole workerGroupRole = new WorkerGroupRole();
		FarmEmployee farmEmployee = farmEmployeeService
				.findFarmEmployeeById(workerGroupFarmEmployeeReqDTO.getEmployeeId()).get();
		EmployeeRole employeeRole = employeeRoleService.findById(workerGroupFarmEmployeeReqDTO.getEmployeeRoleId())
				.get();

		for (WorkerGroupRole workerGroupRoleItr : workerGroupRoleService
				.getWorkerGroupRolesById(workerGroupFarmEmployeeReqDTO.getWorkerGroupId())) {

			if (workerGroupRoleItr.getEmployeeRole().getName().equals(employeeRole.getName())) {
				workerGroupRole = workerGroupRoleItr;
			}
		}

		workerGroupFarmEmployee.setWorkerGroupRole(workerGroupRole);
		workerGroupFarmEmployee.setFarmEmployee(farmEmployee);

		workerGroupFarmEmployeeService.insertWorkerGroupFarmEmployee(workerGroupFarmEmployee);
	}

	@Override
	public WorkerGroupResDTO findWorkerGroupByFarmEmpId(Long farmEmpId) {

		WorkerGroupFarmEmployee workerGroupFarmEmployee = workerGroupFarmEmployeeService
				.findWorkerGroupFarmEmployeeByFarmEmployeeId(farmEmpId).get();

		WorkerGroup workerGroup = workerGroupFarmEmployee.getWorkerGroupRole().getWorkerGroup();
		WorkerGroupResDTO workerGroupResDTO = new WorkerGroupResDTO();

		BeanUtils.copyProperties(workerGroup, workerGroupResDTO);

		return workerGroupResDTO;
	}

	@Override
	public List<EmployeeResDTO> getEmployeeByWorkerGroupIdFilterByMembers(Long workerGroupId) {

		List<EmployeeResDTO> farmEmployees = new ArrayList<EmployeeResDTO>();
		Employee employee = new Employee();
		EmployeeResDTO empResDTO = new EmployeeResDTO();

		for (WorkerGroupRole workerGroupRole : workerGroupRoleService.getWorkerGroupRolesById(workerGroupId)) {
			for (WorkerGroupFarmEmployee workerGroupFarmEmployee : workerGroupRole.getWorkerGroupFarmEmployees()) {
				if (workerGroupFarmEmployee.getWorkerGroupRole().getEmployeeRole().getName().equals("MEMBER")) {
					employee = workerGroupFarmEmployee.getFarmEmployee().getEmployee();
					empResDTO = new EmployeeResDTO();
					empResDTO.setId(workerGroupFarmEmployee.getFarmEmployee().getId());
					empResDTO.setName(employee.getName());
					try {
						if (employee.getUser() == null) {
							empResDTO.setUserActive("false");
						} else if (employee.getUser().isEnabled()) {
							empResDTO.setUserActive("true");
						} else {
							empResDTO.setUserActive("disabled");
						}
					} catch (EntityNotFoundException e) {
						empResDTO.setUserActive("disabled");
					}
					empResDTO.setRole(workerGroupRole.getEmployeeRole().getName());
					farmEmployees.add(empResDTO);
				}
			}
		}

		return farmEmployees;
	}

	public void deleteWorkerGroupById(Long workerGroupId) {
		workerGroupRepo.deleteById(workerGroupId);
	}

}
