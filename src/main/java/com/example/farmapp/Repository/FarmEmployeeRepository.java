package com.example.farmapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.FarmEmployee;

@Repository
public interface FarmEmployeeRepository extends JpaRepository<FarmEmployee, Long>{

}
