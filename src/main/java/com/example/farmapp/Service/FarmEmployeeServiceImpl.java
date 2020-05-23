package com.example.farmapp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.farmapp.Entity.FarmEmployee;
import com.example.farmapp.Repository.FarmEmployeeRepository;

@Service
public class FarmEmployeeServiceImpl implements FarmEmployeeService {

	@Autowired
	private FarmEmployeeRepository farmEmpRepo;

	public Optional<FarmEmployee> findFarmEmployeeById(Long farmEmpId) {

		return farmEmpRepo.findById(farmEmpId);

	}

	@Override
	public String insertFarmEmployee(FarmEmployee farmEmployee) {

		try {
			farmEmpRepo.save(farmEmployee);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}

	}

}
