package com.example.farmapp.Controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.farmapp.Entity.ApplicationRole;
import com.example.farmapp.Entity.User;
import com.example.farmapp.Entity.UserApplicationRole;
import com.example.farmapp.Repository.ApplicationRoleRepository;
import com.example.farmapp.Repository.UserApplicationRoleRepository;
import com.example.farmapp.Repository.UserRepository;
import com.example.farmapp.Service.UserService;
import com.example.farmapp.dto.UserResDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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
	private UserApplicationRoleRepository uAppRoleRepo;

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
}

/*
 * Bootstrap database
 * 
 * Application roles User Employee Type[OWNER,WORKER,MANAGER]
 * employeeRole[LEADER, MEMBER]
 * 
 */