package com.example.farmapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.EmployeeRole;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long>{

	Optional<EmployeeRole> findByName(String name);
}
