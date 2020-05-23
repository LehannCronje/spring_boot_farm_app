package com.example.farmapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.SubUserRegistry;

@Repository
public interface SubUserRegistryRepository extends JpaRepository<SubUserRegistry, Long>{

}
