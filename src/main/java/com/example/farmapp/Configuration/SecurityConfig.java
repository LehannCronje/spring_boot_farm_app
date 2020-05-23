package com.example.farmapp.Configuration;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.farmapp.Security.Jwt.JwtSecurityConfigurer;
import com.example.farmapp.Security.Jwt.JwtTokenProvider;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
		.httpBasic().disable()
		.csrf().disable()
		.cors().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
			.antMatchers("/auth/signin").permitAll()
			.antMatchers("/user/create-app-roles").permitAll()
			.antMatchers("/user/create").permitAll()
			.antMatchers("/**").hasRole("USER")
			.antMatchers("/farms/test").permitAll()
			.anyRequest().authenticated()
		.and()
		.apply(new JwtSecurityConfigurer(jwtTokenProvider));
		
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://18.132.131.55:3500", "http://localhost:3500"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


}
