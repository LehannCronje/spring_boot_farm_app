package com.example.farmapp.Security.Controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmapp.Repository.UserRepository;
import com.example.farmapp.Security.Dao.AuthRequest;
import com.example.farmapp.Security.Jwt.JwtTokenProvider;


@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserRepository users;
	
	@PostMapping("/signin")
	public ResponseEntity signin(@RequestBody AuthRequest data) {
		System.out.println(data.toString());
		try {
			String username = data.getUsername();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new BadCredentialsException("Invlid username/password supplied");
		}
	}
}
