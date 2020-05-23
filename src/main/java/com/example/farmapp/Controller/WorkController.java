package com.example.farmapp.Controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmapp.Domain.WorkDomain;
import com.example.farmapp.Service.WorkService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    @PostMapping("/")
    public void create(@RequestBody WorkDomain workData) {
        workService.createWork(workData);
    }

    @GetMapping("/{id}")
    public Set<Map<String, String>> getWork(@PathVariable("id") Long id) {

        return workService.getWork(id);

    }
}