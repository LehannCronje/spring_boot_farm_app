package com.example.farmapp.Repository;

import java.util.Optional;

import com.example.farmapp.Entity.HourType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourTypeRepository extends JpaRepository<HourType, Long> {

    Optional<HourType> findByName(String name);
}