package com.example.farmapp.Service;

import java.util.Optional;

import com.example.farmapp.Entity.EmployeeRole;

public interface EmployeeRoleService {

	public Optional<EmployeeRole> findByName(String name);
}
