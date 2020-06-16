package com.example.farmapp.schedulers;

import com.example.farmapp.Entity.Employee;
import com.example.farmapp.Repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmployeeHourScheduler {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Scheduled(cron = "0 0 * * * *")
    public void resetEmployeeWorkedHours() {
        System.out.println("EmployeeWorkedHoursReset");
        for (Employee employee : employeeRepo.findAll()) {
            employee.setWorkedHours(0);
            employeeRepo.save(employee);
        }

    }

}