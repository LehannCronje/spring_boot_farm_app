package com.example.farmapp.Domain;

import lombok.Data;

@Data
public class EmployeeDomain {

    private Long farmId;

    private String Name;

    private String Email;
    
    private String type;
    
}