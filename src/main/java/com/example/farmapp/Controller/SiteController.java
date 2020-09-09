package com.example.farmapp.Controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmapp.Domain.SiteDomain;
import com.example.farmapp.Entity.Site;
import com.example.farmapp.Service.SiteService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/sites")
public class SiteController {

    @Autowired
    SiteService siteService;

    @PostMapping("/create")
    public void create(@RequestBody SiteDomain site) {
        System.out.println(siteService.createSite(site));
    }

    @DeleteMapping("/{siteId}")
    public void delete(@PathVariable("siteId") Long siteId) {
        siteService.deleteSite(siteId);
    }

    @GetMapping(value = "/{id}")
    public Set<Map<String, String>> getAllSites(@PathVariable Long id) {
        return siteService.getAllSites(id);
    }

    @GetMapping(value = "site/{siteId}")
    public Site getSite(@PathVariable Long siteId) {
        return siteService.findSiteById(siteId);
    }

    @PostMapping(value = "/update")
    public void addCrops(@RequestBody SiteDomain siteData) {
        System.out.println("hello");
        siteService.addCrops(siteData);
    }

}