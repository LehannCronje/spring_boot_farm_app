package com.example.farmapp.Service;

import java.util.Map;
import java.util.Set;

import com.example.farmapp.dto.EmployeeReqDTO;

/**
 * EmployeeService
 */
public interface EmployeeService {

    public void createEmployee(EmployeeReqDTO EmployeeReqDTO);

    public Set<Map<String, String>> getEmployees(Long farmId);

    public void deleteEmployee(Long empId);

    // public List<Employee> findEmployeesByFarmId(Long FarmId);
}