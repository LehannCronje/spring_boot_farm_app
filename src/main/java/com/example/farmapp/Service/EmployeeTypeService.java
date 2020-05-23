package com.example.farmapp.Service;

import java.util.Optional;

import com.example.farmapp.Entity.EmployeeType;

public interface EmployeeTypeService {

    public Optional<EmployeeType> findEmployeeTypeByName(String name);

}