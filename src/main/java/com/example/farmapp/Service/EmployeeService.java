package com.example.farmapp.Service;

import java.util.Map;
import java.util.Set;

import com.example.farmapp.Entity.Employee;
import com.example.farmapp.dto.EmployeeReqDTO;

/**
 * EmployeeService
 */
public interface EmployeeService {

    public void createEmployee(EmployeeReqDTO EmployeeReqDTO);

    public Set<Map<String, String>> getEmployees(Long farmId);

    public void deleteEmployee(Long empId);

    public String insertEmployee(Employee employee);
    // public List<Employee> findEmployeesByFarmId(Long FarmId);
}