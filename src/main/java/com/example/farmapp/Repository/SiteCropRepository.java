package com.example.farmapp.Repository;

import java.util.Optional;

import com.example.farmapp.Entity.SiteCrop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteCropRepository extends JpaRepository<SiteCrop, Long> {

    @Query(value = "select sc from SiteCrop sc where sc.farmSite.id= ?1 AND sc.crop.id= ?2")
    Optional<SiteCrop> findByFarmSiteIdAndCropId(Long farmSiteId, Long cropId);
}
