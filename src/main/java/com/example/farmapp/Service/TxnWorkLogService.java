package com.example.farmapp.Service;

import java.util.List;

import com.example.farmapp.Entity.TxnWorkLog;
import com.example.farmapp.dto.TxnWorkLogReqDTO;
import com.example.farmapp.dto.TxnWorkLogResDTO;

public interface TxnWorkLogService {

    public void createTxnWorkLog(TxnWorkLogReqDTO txnWorkLogReqDTO);

    public List<TxnWorkLogResDTO> getTxnWorkLogsByFarmSite(Long FarmSiteId);

    public List<TxnWorkLog> getTxnWorkLogBySiteId(Long siteId);

    public List<TxnWorkLog> getTxnWorkLogByFarmId(Long farmId);

    public List<TxnWorkLog> getTxnWorkLogByCropId(Long cropId);

}