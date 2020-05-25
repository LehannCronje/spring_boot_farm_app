package com.example.farmapp.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.farmapp.Domain.EmployeeDomain;
import com.example.farmapp.Entity.Employee;
import com.example.farmapp.Entity.EmployeeType;
import com.example.farmapp.Entity.Farm;
import com.example.farmapp.Entity.FarmEmployee;
import com.example.farmapp.Repository.EmployeeRepository;
import com.example.farmapp.Repository.EmployeeTypeRepository;
import com.example.farmapp.Repository.FarmEmployeeRepository;
import com.example.farmapp.Repository.FarmRepository;
import com.example.farmapp.Repository.UserRepository;
import com.example.farmapp.dto.EmployeeReqDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    FarmRepository farmRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    FarmEmployeeRepository farmEmpRepo;

    @Autowired
    EmployeeTypeRepository empTypeRepo;

    @Autowired
    private FarmService farmService;

    @Autowired
    private FarmEmployeeService farmEmployeeService;

    public void createEmployee(EmployeeReqDTO employeeReqDTO) {

        Employee employee = new Employee();
        Farm farm = farmService.findFarmById(employeeReqDTO.getFarmId()).get();
        FarmEmployee farmEmployee = new FarmEmployee();
        EmployeeType empType = empTypeRepo.findByName(employeeReqDTO.getType()).get();

        employee.setName(employeeReqDTO.getName());
        employee.setEmail(employeeReqDTO.getEmail());
        this.insertEmployee(employee);

        farmEmployee.setEmployee(employee);
        farmEmployee.setFarm(farm);
        farmEmployee.setEmployeeType(empType);
        farmEmployeeService.insertFarmEmployee(farmEmployee);

    }

    public Set<Map<String, String>> getEmployees(Long farmId) {
        Set<Map<String, String>> s = new HashSet<Map<String, String>>();
        Map<String, String> m = new HashMap<String, String>();
        Farm farm = farmRepo.findById(farmId).get();
        EmployeeDomain employeeData = new EmployeeDomain();

        for (FarmEmployee farmEmp : farm.getFarmEmployees()) {
            m = new HashMap<String, String>();
            m.put("id", "" + farmEmp.getId());
            m.put("name", farmEmp.getEmployee().getName());
            m.put("email", farmEmp.getEmployee().getEmail());
            m.put("type", farmEmp.getEmployeeType().getName());

            s.add(m);
        }
        return s;
    }

    public void deleteEmployee(Long empId) {
        employeeRepo.deleteById(empId);
    }

    public String insertEmployee(Employee employee) {
        employeeRepo.save(employee);
        return "Success";
    }

    // @Override
    // public List<Employee> findEmployeesByFarmId(Long FarmId) {

    //     return ;

    // }
}