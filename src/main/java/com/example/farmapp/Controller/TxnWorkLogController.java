package com.example.farmapp.Controller;

import java.util.List;

import com.example.farmapp.Service.TxnWorkLogService;
import com.example.farmapp.dto.TxnWorkLogReqDTO;
import com.example.farmapp.dto.TxnWorkLogResDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/txn-work-log")
public class TxnWorkLogController {

    @Autowired
    private TxnWorkLogService txnWorkLogService;

    @PostMapping(value = "/")
    public void addWorkEntry(@RequestBody() TxnWorkLogReqDTO txnWorkLogReqDTO) {
        System.out.println(txnWorkLogReqDTO.toString());
        txnWorkLogService.createTxnWorkLog(txnWorkLogReqDTO);
    }

    @GetMapping(value = "/farm-site/{farmSiteid}")
    public List<TxnWorkLogResDTO> getWorkLogsForFarmSite(@PathVariable("farmSiteid") Long farmSiteid) {
        return txnWorkLogService.getTxnWorkLogsByFarmSite(farmSiteid);
    }
}