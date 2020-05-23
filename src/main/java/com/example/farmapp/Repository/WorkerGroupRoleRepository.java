package com.example.farmapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.WorkerGroupRole;

@Repository
public interface WorkerGroupRoleRepository extends JpaRepository<WorkerGroupRole, Long>{

	List<WorkerGroupRole> findByWorkerGroupId(Long workerGroupId);
}
