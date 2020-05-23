package com.example.farmapp.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.Crop;

@Repository
public interface CropRepository extends CrudRepository<Crop, Long> {

	Optional<Crop> findByName(String name);
}