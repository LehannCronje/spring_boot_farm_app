package com.example.farmapp.Controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmapp.Domain.FarmDomain;
import com.example.farmapp.Repository.FarmRepository;
import com.example.farmapp.Service.FarmService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/farms")
public class FarmController {

    @Autowired
    FarmService farmService;
    
    @Autowired
    FarmRepository farmRepo;

    @PostMapping(value = "/create")
    public void create(@RequestBody FarmDomain data, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Create Farm");
        farmService.createFarm(data, userDetails.getUsername());

    }

    @PostMapping(value = "/delete")
    public void delete(@RequestBody FarmDomain data) {
        farmService.deleteFarm(data);
    }

    @GetMapping(value = "/")
    public Set<Map<String, String>> getMethodName(@AuthenticationPrincipal UserDetails userDetails) {
        return farmService.getAllFarms(userDetails.getUsername());
    }

    @GetMapping(value = "/test")
    public ResponseEntity test() {
    	return ok(farmRepo.findById(Long.valueOf(""+31)).get().getFarmSite());
    }
}