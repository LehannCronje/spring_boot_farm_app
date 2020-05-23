package com.example.farmapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.SiteCrop;

@Repository
public interface SiteCropRepository extends JpaRepository<SiteCrop, Long>{


}
