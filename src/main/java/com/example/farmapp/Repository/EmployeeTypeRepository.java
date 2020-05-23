package com.example.farmapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.EmployeeType;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long>{

	Optional<EmployeeType> findByName(String name);
	
}
