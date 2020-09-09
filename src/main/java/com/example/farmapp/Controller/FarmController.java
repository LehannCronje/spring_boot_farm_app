package com.example.farmapp.Controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Map;
import java.util.Set;

import com.example.farmapp.Domain.FarmDomain;
import com.example.farmapp.Repository.FarmRepository;
import com.example.farmapp.Service.FarmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/farms")
public class FarmController {

    @Autowired
    FarmService farmService;

    @Autowired
    FarmRepository farmRepo;

    //    @CacheEvict(value = "farms", key = "#userDetails.username")
    @PostMapping(value = "/create")
    public void create(@RequestBody FarmDomain data, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Create Farm");
        farmService.createFarm(data, userDetails.getUsername());

    }

    //    @CacheEvict(value = "farms", key = "#userDetails.username")
    @DeleteMapping(value = "/{farmId}")
    public void delete(@PathVariable("farmId") Long farmId, @AuthenticationPrincipal UserDetails userDetails) {
        farmService.deleteFarm(farmId);
    }

    //    @Cacheable(value = "farms", key = "#userDetails.username")
    @GetMapping(value = "/")
    public Set<Map<String, String>> getMethodName(@AuthenticationPrincipal UserDetails userDetails) {
        return farmService.getAllFarms(userDetails.getUsername());
    }

    @GetMapping(value = "/test")
    public ResponseEntity test() {
        return ok(farmRepo.findById(Long.valueOf("" + 31)).get().getFarmSite());
    }

    @GetMapping("/clear-cache")
//    @CacheEvict(cacheNames = "farms", allEntries = true)
    public void clearCache(@AuthenticationPrincipal UserDetails userDetails) {

    }
}