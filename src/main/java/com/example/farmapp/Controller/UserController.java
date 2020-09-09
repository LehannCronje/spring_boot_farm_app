package com.example.farmapp.Controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.farmapp.Entity.ApplicationRole;
import com.example.farmapp.Entity.EmployeeRole;
import com.example.farmapp.Entity.EmployeeType;
import com.example.farmapp.Entity.HourType;
import com.example.farmapp.Entity.User;
import com.example.farmapp.Entity.UserApplicationRole;
import com.example.farmapp.Repository.ApplicationRoleRepository;
import com.example.farmapp.Repository.EmployeeRoleRepository;
import com.example.farmapp.Repository.EmployeeTypeRepository;
import com.example.farmapp.Repository.HourTypeRepository;
import com.example.farmapp.Repository.UserRepository;
import com.example.farmapp.Service.UserService;
import com.example.farmapp.dto.UserResDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ApplicationRoleRepository appRoleRepo;

	@Autowired
	private EmployeeTypeRepository empTypeRepo;

	@Autowired
	private EmployeeRoleRepository empRoleRepo;

	@Autowired
	private HourTypeRepository hourTypeRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userService;

	@SuppressWarnings("rawtypes")
	@GetMapping("/me")
	public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
		Map<Object, Object> model = new HashMap<>();
		try {
			model.put("username", userDetails.getUsername());
			model.put("roles", userDetails.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority())
					.collect(toList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok(model);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("userId") Long userId) {
		userService.deleteUserByUserId(userDetails.getUsername(), userId);
	}

	@PostMapping("/enable/{farmEmpId}")
	public void enableUser(@PathVariable("farmEmpId") Long farmEmpId,
			@AuthenticationPrincipal UserDetails userDetails) {
		userService.enableUser(farmEmpId, userDetails.getUsername());
	}

	@GetMapping("/create")
	public void createUser() {

		User u = new User();
		ApplicationRole appRole = appRoleRepo.findByRole("ROLE_USER").get();
		UserApplicationRole uar = new UserApplicationRole();

		uar.setApplicationRole(appRole);
		uar.setUser(u);

		u.setUsername("Lehann");
		u.setPassword(this.passwordEncoder.encode("password"));

		Set<UserApplicationRole> uarList = u.getUserApplicationRoles();
		uarList.add(uar);
		u.setUserApplicationRoles(uarList);

		userRepo.save(u);

	}

	@GetMapping("/create-app-roles")
	public void createAppRoles() {
		ApplicationRole arUser = new ApplicationRole("ROLE_ADMIN");
		ApplicationRole arAdmin = new ApplicationRole("ROLE_USER");

		List<ApplicationRole> appRoles = Arrays.asList(arUser, arAdmin);

		appRoleRepo.saveAll(appRoles);
	}

	@GetMapping("/user-registry")
	public List<UserResDTO> getUserRegistries(@AuthenticationPrincipal UserDetails userDetials) {
		return userService.getUserRegistries(userDetials.getUsername());
	}

	@GetMapping("/bootstrap")
	public void bootstrapdb() {
		this.createAppRoles();
		this.createUser();

		EmployeeType owner = new EmployeeType();
		EmployeeType worker = new EmployeeType();
		EmployeeType manager = new EmployeeType();

		owner.setName("OWNER");
		worker.setName("WORKER");
		manager.setName("MANAGER");

		empTypeRepo.save(owner);
		empTypeRepo.save(worker);
		empTypeRepo.save(manager);

		EmployeeRole leader = new EmployeeRole();
		EmployeeRole member = new EmployeeRole();

		leader.setName("LEADER");
		member.setName("MEMBER");

		empRoleRepo.save(leader);
		empRoleRepo.save(member);

		HourType normal = new HourType();
		HourType overtime = new HourType();
		HourType special = new HourType();

		normal.setHourlyRate(Double.valueOf("" + 1));
		normal.setName("NORMAL");

		overtime.setHourlyRate(1.5);
		overtime.setName("OVERTIME");

		special.setHourlyRate(Double.valueOf("" + 2));
		special.setName("SPECIAL");
		hourTypeRepo.save(normal);
		hourTypeRepo.save(overtime);
		hourTypeRepo.save(special);

	}
}

/*
 * Bootstrap database
 * 
 * Application roles User Employee Type[OWNER,WORKER,MANAGER]
 * employeeRole[LEADER, MEMBER]
 * 
 */