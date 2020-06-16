package com.example.farmapp.Service;

import java.util.Date;
import java.util.Optional;

import com.example.farmapp.Entity.Employee;
import com.example.farmapp.Entity.HourType;

public interface HourTypeService {

    public Optional<HourType> findHourTypeByName(String name);

    public HourType getHourType(Employee employee);
}