package com.example.farmapp.Repository;

import java.util.Optional;

import com.example.farmapp.Entity.CropWork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CropWorkRepository extends JpaRepository<CropWork, Long> {

    @Query(value = "select cw from CropWork cw where cw.siteCrop.id= ?1 AND cw.work.id= ?2")
    Optional<CropWork> findBySiteCropIdAndWorkId(Long siteCropId, Long workId);
}
