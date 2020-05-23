package com.example.farmapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.WorkerGroupFarmEmployee;

@Repository
public interface WorkerGroupFarmEmployeeRepository extends JpaRepository<WorkerGroupFarmEmployee, Long>{

}
