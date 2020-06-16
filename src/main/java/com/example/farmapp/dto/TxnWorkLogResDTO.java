package com.example.farmapp.dto;

import lombok.Data;

@Data
public class TxnWorkLogResDTO {

    private Long id;

    private String employeeName;

    private String employeeSurname;

    private String hours;

    private String hourType;

    private String tarif;

    private String total;

    private String work;

    private String timeWorked;

}