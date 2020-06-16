package com.example.farmapp.Service;

import java.util.List;
import java.util.Optional;

import com.example.farmapp.Entity.User;
import com.example.farmapp.dto.UserReqDTO;
import com.example.farmapp.dto.UserResDTO;

public interface UserService {

	public void createUserByFarmEmpId(UserReqDTO userReqDTO, String username);

	public List<UserResDTO> getUserRegistries(String username);

	public Optional<User> findUserByUsername(String username);

}
