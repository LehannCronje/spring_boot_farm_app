package com.example.farmapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>{

}
