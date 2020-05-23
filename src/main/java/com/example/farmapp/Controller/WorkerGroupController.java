package com.example.farmapp.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmapp.Service.UserService;
import com.example.farmapp.Service.WorkerGroupService;
import com.example.farmapp.dto.EmployeeResDTO;
import com.example.farmapp.dto.UserReqDTO;
import com.example.farmapp.dto.WorkerGroupFarmEmployeeReqDTO;
import com.example.farmapp.dto.WorkerGroupReqDTO;
import com.example.farmapp.dto.WorkerGroupResDTO;

@RestController()
@RequestMapping("/worker-group")
public class WorkerGroupController {
	// Still needs clean up work
	@Autowired
	private WorkerGroupService workerGroupService;

	@Autowired
	private UserService userService;
	
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
		
		
		workerGroupService.addWorkerGroupEmployee(workerGroupFarmEmpReqDTO);
		
	}
	
	@PostMapping(value = "/farm-employee/create-user")
	public void createUser(@RequestBody() UserReqDTO userReqDTO, @AuthenticationPrincipal UserDetails userDetails) {
		
	
		userService.createUserByFarmEmpId(userReqDTO, userDetails.getUsername());
		
	}
}
