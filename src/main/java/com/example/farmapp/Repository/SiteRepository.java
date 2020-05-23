package com.example.farmapp.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.farmapp.Entity.Site;

@Repository
public interface SiteRepository extends CrudRepository<Site, Long> {

	Optional<Site> findByName(String name);
	
}