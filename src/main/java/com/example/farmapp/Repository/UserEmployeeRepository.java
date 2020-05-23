package com.example.farmapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.UserEmployee;

@Repository
public interface UserEmployeeRepository extends JpaRepository<UserEmployee, Long>{

}
