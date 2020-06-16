package com.example.farmapp.Controller;

import java.util.List;
import java.util.Set;

import com.example.farmapp.Entity.FarmEmployee;
import com.example.farmapp.Service.FarmEmployeeService;
import com.example.farmapp.Service.UserService;
import com.example.farmapp.Service.WorkerGroupService;
import com.example.farmapp.dto.EmployeeResDTO;
import com.example.farmapp.dto.UserReqDTO;
import com.example.farmapp.dto.WorkerGroupFarmEmployeeReqDTO;
import com.example.farmapp.dto.WorkerGroupReqDTO;
import com.example.farmapp.dto.WorkerGroupResDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/worker-group")
public class WorkerGroupController {
	// Still needs clean up work
	@Autowired
	private WorkerGroupService workerGroupService;

	@Autowired
	private UserService userService;

	@Autowired
	private FarmEmployeeService farmEmployeeService;

	@PostMapping(value = "/")
	public void createWorkerGroup(@RequestBody() WorkerGroupReqDTO workerGroupReqDto) {

		System.out.println(workerGroupReqDto.toString());
		workerGroupService.createWorkerGroup(workerGroupReqDto);

	}

	@GetMapping(value = "/{farmId}")
	public Set<WorkerGroupResDTO> getWorkerGroups(@PathVariable("farmId") Long farmId) {

		return workerGroupService.getWorkerGroups(farmId);

	}

	@GetMapping(value = "/farm-employees/{workerGroupId}")
	public List<EmployeeResDTO> getEmployeesByWorkerGroupId(@PathVariable("workerGroupId") Long workerGroupId) {
		return workerGroupService.getEmployeeByWorkerGroupId(workerGroupId);
	}

	@PostMapping(value = "/farm-employees")
	public void addWorkerGroupMember(@RequestBody() WorkerGroupFarmEmployeeReqDTO workerGroupFarmEmpReqDTO) {
		System.out.println(workerGroupFarmEmpReqDTO.toString());
		workerGroupService.addWorkerGroupEmployee(workerGroupFarmEmpReqDTO);

	}

	@PostMapping(value = "/farm-employee/create-user")
	public void createUser(@RequestBody() UserReqDTO userReqDTO, @AuthenticationPrincipal UserDetails userDetails) {

		userService.createUserByFarmEmpId(userReqDTO, userDetails.getUsername());

	}

	@GetMapping(value = "/farm-emp/{farmEmpId}")
	public WorkerGroupResDTO getWorkerGroupByFarmEmployeeId(@PathVariable("farmEmpId") Long farmEmpId) {

		return workerGroupService.findWorkerGroupByFarmEmpId(farmEmpId);

	}

	@GetMapping(value = "/farm-employees/worker-group/member/{workerGroupId}")
	public List<EmployeeResDTO> getEmployeesByWorkerGroupIdFilterByMember(
			@PathVariable("workerGroupId") Long workerGroupId) {
		return workerGroupService.getEmployeeByWorkerGroupIdFilterByMembers(workerGroupId);
	}

	@GetMapping(value = "/farm-emp/{farmId}/username")
	public List<EmployeeResDTO> getWorkerGroupEmployeeByFarmIdAndUsername(@PathVariable("farmId") Long farmId,
			@AuthenticationPrincipal UserDetails userDetails) {

		FarmEmployee farmEmployee = farmEmployeeService.findFarmEmployeeByFarmIdAndUsername(farmId,
				userDetails.getUsername());
		Long workerGroupId = workerGroupService.findWorkerGroupByFarmEmpId(farmEmployee.getId()).getId();

		return workerGroupService.getEmployeeByWorkerGroupIdFilterByMembers(workerGroupId);

	}
}
