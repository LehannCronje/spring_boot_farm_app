package com.example.farmapp.Service;

import java.util.Optional;

import com.example.farmapp.Entity.EmployeeType;
import com.example.farmapp.Repository.EmployeeTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeTypeServiceImpl implements EmployeeTypeService {

    @Autowired
    private EmployeeTypeRepository employeeTypeRepo;

    @Override
    public Optional<EmployeeType> findEmployeeTypeByName(String name) {

        return employeeTypeRepo.findByName(name);

    }

}