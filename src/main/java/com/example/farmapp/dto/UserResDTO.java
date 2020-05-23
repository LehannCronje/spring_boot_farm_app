package com.example.farmapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserResDTO {

	private Long id;

	private List<String> role;

	private String username;
}
