package com.example.farmapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.ApplicationRole;

@Repository
public interface ApplicationRoleRepository extends JpaRepository<ApplicationRole, Long> {

	Optional<ApplicationRole> findByRole(String role);
	
}
