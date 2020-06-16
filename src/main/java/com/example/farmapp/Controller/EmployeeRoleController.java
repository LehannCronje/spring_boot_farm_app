package com.example.farmapp.Controller;

import java.util.List;

import com.example.farmapp.Service.EmployeeRoleService;
import com.example.farmapp.dto.EmployeeRoleResDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee-role")
public class EmployeeRoleController {

    @Autowired
    EmployeeRoleService employeeRoleService;

    @GetMapping(value = "/")
    public List<EmployeeRoleResDTO> getEmployeeRoles() {
        return employeeRoleService.findAllEmployeeRoles();
    }

}
