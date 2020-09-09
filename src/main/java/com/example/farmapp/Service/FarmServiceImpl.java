package com.example.farmapp.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.farmapp.Domain.FarmDomain;
import com.example.farmapp.Entity.Employee;
import com.example.farmapp.Entity.EmployeeType;
import com.example.farmapp.Entity.Farm;
import com.example.farmapp.Entity.FarmEmployee;
import com.example.farmapp.Entity.User;
import com.example.farmapp.Entity.UserEmployee;
import com.example.farmapp.Repository.EmployeeRepository;
import com.example.farmapp.Repository.EmployeeTypeRepository;
import com.example.farmapp.Repository.FarmEmployeeRepository;
import com.example.farmapp.Repository.FarmRepository;
import com.example.farmapp.Repository.UserEmployeeRepository;
import com.example.farmapp.Repository.UserRepository;

@Service
public class FarmServiceImpl implements FarmService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserEmployeeRepository userEmpRepo;

	@Autowired
	FarmRepository farmRepo;

	@Autowired
	FarmEmployeeRepository farmEmpRepo;

	@Autowired
	EmployeeTypeRepository empTypeRepo;

	@Autowired
	EmployeeRepository empRepo;

	public void createFarm(FarmDomain data, String username) {

		User currentUser = userRepo.findByUsername(username).get();
		Set<UserEmployee> userEmployees = new HashSet<UserEmployee>();
		Set<FarmEmployee> farmEmployees = new HashSet<FarmEmployee>();
		UserEmployee userEmployee = new UserEmployee();
		Employee employee = new Employee();
		FarmEmployee farmEmployee = new FarmEmployee();
		Farm farm = new Farm();

		// Find employee type. On creation of farm employee type should always be Owner
		EmployeeType empType = empTypeRepo.findByName("OWNER").get();

		if (currentUser.getEmployee() == null) {
			// creating the employee
			employee.setName(currentUser.getUsername());
			employee.setEmail("" + currentUser.getUsername() + "@gmail.com");
			employee.setUser(currentUser);
			empRepo.save(employee);

			currentUser.setEmployee(employee);
			userRepo.save(currentUser);

		} else {

			employee = currentUser.getEmployee();
		}

		System.out.println(data.toString());
		// Creating the farm
		farm.setName(data.getName());
		farmRepo.save(farm);

		// linking the farm to employee
		farmEmployee.setEmployee(employee);
		farmEmployee.setFarm(farm);
		farmEmployee.setEmployeeType(empType);
		farmEmployees.add(farmEmployee);
		farmEmpRepo.save(farmEmployee);

		employee.setFarmEmployees(farmEmployees);
		empRepo.save(employee);

	}

	public Set<Map<String, String>> getAllFarms(String username) {

		User currentUser = userRepo.findByUsername(username).get();

		Set<Map<String, String>> s = new HashSet<Map<String, String>>();
		Map<String, String> m = new HashMap<String, String>();

		if (currentUser.getEmployee() == null) {

			return s;
		}

		for (FarmEmployee farmEmployee : currentUser.getEmployee().getFarmEmployees()) {
			m = new HashMap<String, String>();
			m.put("id", "" + farmEmployee.getFarm().getId());
			m.put("name", farmEmployee.getFarm().getName());
			s.add(m);
		}

		return s;
	}

	@Override
	public void deleteFarm(Long farmId) {

		farmRepo.deleteById(farmId);

	}

	public Optional<Farm> findFarmById(Long farmId) {
		return farmRepo.findById(farmId);
	}

	public String insertFarm(Farm farm) {
		try {
			farmRepo.save(farm);
			return "Success";

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
}