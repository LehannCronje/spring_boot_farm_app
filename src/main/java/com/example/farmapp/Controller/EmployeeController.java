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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmapp.Domain.EmployeeDomain;
import com.example.farmapp.Service.EmployeeService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/")
    public void addEmployee(@RequestBody EmployeeDomain employeeData) {
    	
        employeeService.createEmployee(employeeData);
    }

    @GetMapping(value = "/{farmId}")
    public Set<Map<String, String>> getEmployees(@PathVariable("farmId") Long farmId) {
        return employeeService.getEmployees(farmId);
    }

    @PostMapping(value = "/delete")
    public void deleteEmployee(@RequestParam("id") Long empId) {
        employeeService.deleteEmployee(empId);
    }
}