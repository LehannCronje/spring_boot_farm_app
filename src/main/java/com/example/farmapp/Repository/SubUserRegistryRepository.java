package com.example.farmapp.Repository;

import com.example.farmapp.Entity.SubUserRegistry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubUserRegistryRepository extends JpaRepository<SubUserRegistry, Long> {

    @Query(value = "update SubUserRegistry sur set sur.deleteDate = null where sur.user.id = ?1")
    public void restoreByUserId(Long userId);
}
