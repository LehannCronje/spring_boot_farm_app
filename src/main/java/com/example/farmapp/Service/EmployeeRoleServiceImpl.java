package com.example.farmapp.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.farmapp.Entity.EmployeeRole;
import com.example.farmapp.Repository.EmployeeRoleRepository;
import com.example.farmapp.dto.EmployeeRoleResDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

	@Autowired
	private EmployeeRoleRepository employeeRoleRepo;

	@Override
	public Optional<EmployeeRole> findByName(String name) {

		return employeeRoleRepo.findByName(name);
	}

	@Override
	public List<EmployeeRoleResDTO> findAllEmployeeRoles() {

		return employeeRoleRepo.findAll().stream().map(employeeRole -> {
			EmployeeRoleResDTO employeeRoleResDTO = new EmployeeRoleResDTO();
			employeeRoleResDTO.setId(employeeRole.getId());
			employeeRoleResDTO.setName(employeeRole.getName());
			return employeeRoleResDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<EmployeeRole> findById(Long id) {

		return employeeRoleRepo.findById(id);

	}

}
