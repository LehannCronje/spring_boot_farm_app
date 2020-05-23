package com.example.farmapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.Farm;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

}