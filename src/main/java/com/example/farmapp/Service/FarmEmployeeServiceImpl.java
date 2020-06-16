package com.example.farmapp.Service;

import java.util.Optional;

import com.example.farmapp.Entity.Farm;
import com.example.farmapp.Entity.FarmEmployee;
import com.example.farmapp.Entity.User;
import com.example.farmapp.Repository.FarmEmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmEmployeeServiceImpl implements FarmEmployeeService {

	@Autowired
	private FarmEmployeeRepository farmEmpRepo;

	@Autowired
	private FarmService farmService;

	@Autowired
	private UserService userService;

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

	@Override
	public FarmEmployee findFarmEmployeeByFarmIdAndUsername(Long farmId, String username) {
		Farm farm = farmService.findFarmById(farmId).get();
		User currentUser = userService.findUserByUsername(username).get();

		for (FarmEmployee farmEmployee : currentUser.getEmployee().getFarmEmployees()) {
			if (farmEmployee.getFarm().equals(farm)) {
				return farmEmployee;
			}
		}

		return null;
	}

}
