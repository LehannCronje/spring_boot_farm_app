package com.example.farmapp.Service;

import java.util.Optional;

import com.example.farmapp.Entity.FarmSite;

public interface FarmSiteService {

    public String insertFarmSite(FarmSite farmSite);

    public Optional<FarmSite> findFarmSiteByFarmIdAndSiteId(Long farmId, Long siteId);

    public Optional<FarmSite> findFarmSiteById(Long farmSiteId);
}