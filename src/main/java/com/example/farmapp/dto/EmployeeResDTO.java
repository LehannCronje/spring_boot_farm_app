package com.example.farmapp.dto;

import lombok.Data;

@Data
public class EmployeeResDTO {

	private Long id;
	
	private String name;
	
	private boolean hasUser;
	
	private String role;
}
