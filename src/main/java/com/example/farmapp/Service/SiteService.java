package com.example.farmapp.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.example.farmapp.Domain.SiteDomain;
import com.example.farmapp.Entity.Site;

public interface SiteService {

    public String createSite(SiteDomain siteData);

    public Set<Map<String, String>> getAllSites(Long uid);

    public void deleteSite(Long id);

    public Site findSiteById(Long siteId);

    public Optional<Site> findSiteByName(String name);

    public void addCrops(SiteDomain siteData);

    public String insertSite(Site site);

}