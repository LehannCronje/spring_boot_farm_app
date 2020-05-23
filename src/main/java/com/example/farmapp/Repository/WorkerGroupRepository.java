package com.example.farmapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.WorkerGroup;

@Repository
public interface WorkerGroupRepository extends JpaRepository<WorkerGroup, Long>{

}
