package com.example.farmapp.Service;

import java.util.List;
import java.util.Optional;

import com.example.farmapp.Entity.EmployeeRole;
import com.example.farmapp.dto.EmployeeRoleResDTO;

public interface EmployeeRoleService {

	public Optional<EmployeeRole> findByName(String name);

	public Optional<EmployeeRole> findById(Long id);

	public List<EmployeeRoleResDTO> findAllEmployeeRoles();
}
