package com.example.farmapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.farmapp.Entity.ApplicationRole;
import com.example.farmapp.Entity.Employee;
import com.example.farmapp.Entity.FarmEmployee;
import com.example.farmapp.Entity.SubUserRegistry;
import com.example.farmapp.Entity.User;
import com.example.farmapp.Entity.UserApplicationRole;
import com.example.farmapp.Repository.ApplicationRoleRepository;
import com.example.farmapp.Repository.EmployeeRepository;
import com.example.farmapp.Repository.SubUserRegistryRepository;
import com.example.farmapp.Repository.UserRepository;
import com.example.farmapp.dto.UserReqDTO;
import com.example.farmapp.dto.UserResDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	ApplicationRoleRepository appRoleRepo;

	@Autowired
	FarmEmployeeService farmEmployeeService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	SubUserRegistryRepository subUserRegistryRepo;

	@Override
	public void createUserByFarmEmpId(UserReqDTO userReqDTO, String username) {

		User u = new User();
		ApplicationRole appRole = appRoleRepo.findByRole("ROLE_USER").get();
		UserApplicationRole uar = new UserApplicationRole();
		FarmEmployee farmEmployee = farmEmployeeService.findFarmEmployeeById(userReqDTO.getFarmEmpId()).get();
		Employee employee = farmEmployee.getEmployee();
		User currentUser = userRepo.findByUsername(username).get();
		SubUserRegistry subUserRegistry = new SubUserRegistry();

		uar.setApplicationRole(appRole);
		uar.setUser(u);

		u.setUsername(userReqDTO.getUsername());
		u.setPassword(this.passwordEncoder.encode(userReqDTO.getPassword()));
		u.setEmployee(farmEmployee.getEmployee());

		Set<UserApplicationRole> uarList = u.getUserApplicationRoles();
		uarList.add(uar);
		u.setUserApplicationRoles(uarList);

		userRepo.save(u);

		employee.setUser(u);
		empRepo.save(employee);

		subUserRegistry.setCreatedByUser(currentUser);
		subUserRegistry.setUser(u);

		subUserRegistryRepo.save(subUserRegistry);
	}

	@Override
	public List<UserResDTO> getUserRegistries(String username) {

		User currentUser = userRepo.findByUsername(username).get();
		UserResDTO userResDTO = new UserResDTO();
		List<UserResDTO> userResList = new ArrayList<UserResDTO>();
		for (SubUserRegistry subUserRegistry : currentUser.getSubUsers()) {
			userResDTO = new UserResDTO();
			if (subUserRegistry.getUser() != null) {
				userResDTO.setRole(subUserRegistry.getUser().getUserApplicationRoles().stream()
						.map(UserApplicationRole::getApplicationRole).map(ApplicationRole::getRole)
						.collect(Collectors.toList()));
				userResDTO.setUsername(subUserRegistry.getUser().getUsername());
				userResDTO.setId(subUserRegistry.getUser().getId());
				System.out.println(subUserRegistry.getUser().toString());
				userResList.add(userResDTO);
			}
		}

		return userResList;
	}

	@Override
	public Optional<User> findUserByUsername(String username) {

		return userRepo.findByUsername(username);
	}

	@Override
	public void deleteUserByUserId(String username, Long userId) {

		User user = userRepo.findByUsername(username).get();
		User subUser = new User();

		for (SubUserRegistry subUserRegistry : user.getSubUsers()) {
			if (subUserRegistry.getUser().getId().equals(userId)) {
				subUser = subUserRegistry.getUser();
				user.deleteSubUser(subUserRegistry);
				subUserRegistryRepo.delete(subUserRegistry);
			}
		}

		userRepo.save(user);

		subUser.setIsActive(null);
		userRepo.save(subUser);
	}

	@Override
	public void enableUser(Long farmEmpId, String username) {
		FarmEmployee farmEmp = farmEmployeeService.findFarmEmployeeById(farmEmpId).get();
		User currentUser = userRepo.findByUsername(username).get();
		User user = farmEmp.getEmployee().getUser();
		user.setIsActive("ACTIVE");
		userRepo.save(user);

		SubUserRegistry subUserRegistry = new SubUserRegistry();
		subUserRegistry.setCreatedByUser(currentUser);
		subUserRegistry.setUser(user);
		subUserRegistryRepo.save(subUserRegistry);
	}
}
