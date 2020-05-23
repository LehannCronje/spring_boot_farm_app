package com.example.farmapp.Service;

import java.util.List;

import com.example.farmapp.dto.UserReqDTO;
import com.example.farmapp.dto.UserResDTO;

public interface UserService {

	public void createUserByFarmEmpId(UserReqDTO userReqDTO, String username);

	public List<UserResDTO> getUserRegistries(String username);

}
