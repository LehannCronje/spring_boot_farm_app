package com.example.farmapp.dto;

import lombok.Data;

@Data
public class TxnWorkLogReqDTO {

    private Long siteId;

    private Long cropId;

    private Long workId;

    private String startTime;

    private String endTime;

    private Long farmEmpId;
}