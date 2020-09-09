package com.example.farmapp.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.example.farmapp.Domain.FarmDomain;
import com.example.farmapp.Entity.Farm;

public interface FarmService {

    public void createFarm(FarmDomain data, String username);

    public Set<Map<String, String>> getAllFarms(String username);

    public void deleteFarm(Long farmId);

    public Optional<Farm> findFarmById(Long farmId);

    public String insertFarm(Farm farm);
}