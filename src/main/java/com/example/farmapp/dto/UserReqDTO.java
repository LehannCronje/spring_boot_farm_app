package com.example.farmapp.dto;

import lombok.Data;

@Data
public class UserReqDTO {

	private String username;
	
	private String password;
	
	private Long farmEmpId;
}
