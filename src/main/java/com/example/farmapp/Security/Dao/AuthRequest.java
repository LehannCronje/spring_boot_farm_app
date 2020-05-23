package com.example.farmapp.Security.Dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
	private static final long serialVersionUID = -6986746375915710855L;
	private String username;
    private String password;
}
