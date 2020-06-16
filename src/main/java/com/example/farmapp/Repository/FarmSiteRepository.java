package com.example.farmapp.Repository;

import java.util.Optional;

import com.example.farmapp.Entity.FarmSite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmSiteRepository extends JpaRepository<FarmSite, Long> {

	@Query(value = "select fs from FarmSite fs where fs.farm.id= ?1 AND fs.site.id= ?2")
	Optional<FarmSite> findByFarmIdAndSiteId(Long farmId, Long siteId);

}
