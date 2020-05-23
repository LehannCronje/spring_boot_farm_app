package com.example.farmapp.dto;

import lombok.Data;

@Data
public class EmployeeReqDTO {

    private Long farmId;

    private String Name;

    private String Email;

    private String type;

}