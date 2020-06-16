package com.example.farmapp.Repository;

import java.util.Optional;

import com.example.farmapp.Entity.FarmEmployee;
import com.example.farmapp.Entity.WorkerGroupFarmEmployee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerGroupFarmEmployeeRepository extends JpaRepository<WorkerGroupFarmEmployee, Long> {

    public Optional<WorkerGroupFarmEmployee> findByFarmEmployeeId(Long farmEmployeeId);
}
