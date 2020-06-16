package com.example.farmapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class ReportResDTO {

    private String reportName;

    private String reportPdfName;

    private String creator;

    private String date;

    private List<TxnWorkLogResDTO> logs;

    private int total;

}