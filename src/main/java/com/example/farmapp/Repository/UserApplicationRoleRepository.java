package com.example.farmapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.UserApplicationRole;

@Repository
public interface UserApplicationRoleRepository extends JpaRepository<UserApplicationRole, Long>{

}
