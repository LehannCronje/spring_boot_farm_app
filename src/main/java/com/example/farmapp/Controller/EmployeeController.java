package com.example.farmapp.Controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmapp.Domain.EmployeeDomain;
import com.example.farmapp.Service.EmployeeService;
import com.example.farmapp.dto.EmployeeReqDTO;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/")
    public void addEmployee(@RequestBody EmployeeReqDTO employeeReqDTO) {

        employeeService.createEmployee(employeeReqDTO);
    }

    @GetMapping(value = "/{farmId}")
    public Set<Map<String, String>> getEmployees(@PathVariable("farmId") Long farmId) {
        return employeeService.getEmployees(farmId);
    }

    @DeleteMapping(value = "/{empId}")
    public void deleteEmployee(@PathVariable("empId") Long empId) {
        employeeService.deleteEmployee(empId);
    }
}