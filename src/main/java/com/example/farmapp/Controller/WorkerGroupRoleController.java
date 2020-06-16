package com.example.farmapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmapp.Entity.WorkerGroupRole;
import com.example.farmapp.Service.WorkerGroupRoleService;

@RestController()
@RequestMapping("/worker-group-role")
public class WorkerGroupRoleController {

	@Autowired
	private WorkerGroupRoleService workerGroupRoleService;

	@GetMapping(value = "/{workerGroupId}")
	public List<WorkerGroupRole> getWorkerGroupRolesByWorkerGroupId(@PathVariable("workerGroupId") Long workerGroupId) {

		return workerGroupRoleService.getWorkerGroupRolesById(workerGroupId);

	}

}
