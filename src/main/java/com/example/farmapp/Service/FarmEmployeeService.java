package com.example.farmapp.Service;

import java.util.Optional;

import com.example.farmapp.Entity.FarmEmployee;

public interface FarmEmployeeService {

	public Optional<FarmEmployee> findFarmEmployeeById(Long id);

	public String insertFarmEmployee(FarmEmployee farmEmployee);

}
