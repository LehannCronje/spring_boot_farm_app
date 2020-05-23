package com.example.farmapp.Service;

import com.example.farmapp.Entity.FarmSite;
import com.example.farmapp.Repository.FarmSiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmSiteServiceImpl implements FarmSiteService {

    @Autowired
    private FarmSiteRepository farmSiteRepo;

    @Override
    public String insertFarmSite(FarmSite farmSite) {
        try {
            farmSiteRepo.save(farmSite);
            return "Success";

        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

}