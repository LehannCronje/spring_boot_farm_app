package com.example.farmapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.CropWork;

@Repository
public interface CropWorkRepository extends JpaRepository<CropWork, Long>{

	
}
