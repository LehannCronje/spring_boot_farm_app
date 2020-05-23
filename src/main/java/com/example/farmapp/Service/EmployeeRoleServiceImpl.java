package com.example.farmapp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.farmapp.Entity.EmployeeRole;
import com.example.farmapp.Repository.EmployeeRoleRepository;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService{

	@Autowired
	private EmployeeRoleRepository employeeRoleRepo;
	
	@Override
	public Optional<EmployeeRole> findByName(String name) {
		
		return employeeRoleRepo.findByName(name);
	}

	
}
