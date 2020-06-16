package com.example.farmapp.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.farmapp.Entity.Crop;
import com.example.farmapp.Entity.Farm;
import com.example.farmapp.Entity.HourType;
import com.example.farmapp.Entity.Report;
import com.example.farmapp.Entity.Site;
import com.example.farmapp.Entity.TxnWorkLog;
import com.example.farmapp.Entity.User;
import com.example.farmapp.Repository.ReportRepository;
import com.example.farmapp.dto.ReportResDTO;
import com.example.farmapp.dto.TxnWorkLogResDTO;
import com.example.farmapp.util.PdfGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    TxnWorkLogService txnWorkLogService;

    @Autowired
    PdfGenerator pdfGenerator;

    @Autowired
    UserService userService;

    @Autowired
    ReportRepository reportRepo;

    @Autowired
    SiteService siteService;

    @Autowired
    CropService cropService;

    @Autowired
    FarmService farmService;

    @Override
    public Report generateSiteLabourReport(Long siteId, String username) {

        List<TxnWorkLogResDTO> logList = new ArrayList<TxnWorkLogResDTO>();
        ReportResDTO reportResDTO = new ReportResDTO();
        TxnWorkLogResDTO txnWorkLogResDTO = new TxnWorkLogResDTO();

        User user = userService.findUserByUsername(username).get();
        Report report = new Report();
        LocalDateTime currentDate = LocalDateTime.now();
        Site site = siteService.findSiteById(siteId);

        int reportTotal = 0;

        report.setDate("" + currentDate);
        report.setName("siteLabour" + username + site.getName());
        report.setLocation("./pdf/" + report.getName() + ".pdf");
        report.setUser(user);
        this.insertReport(report);
        for (TxnWorkLog txnWorkLog : txnWorkLogService.getTxnWorkLogBySiteId(siteId)) {
            txnWorkLogResDTO = new TxnWorkLogResDTO();

            txnWorkLogResDTO.setId(txnWorkLog.getId());
            txnWorkLogResDTO.setEmployeeName(txnWorkLog.getEmployee().getName());
            txnWorkLogResDTO.setEmployeeSurname(txnWorkLog.getEmployee().getSurname());
            txnWorkLogResDTO
                    .setHours("" + (txnWorkLog.getTotalHours() / 60) + "h" + (txnWorkLog.getTotalHours() % 60) + "min");
            txnWorkLogResDTO.setHourType(txnWorkLog.getHourType().getName());
            txnWorkLogResDTO.setTarif("" + txnWorkLog.getHourType().getHourlyRate());
            txnWorkLogResDTO.setTotal(calculateCost(txnWorkLog));
            logList.add(txnWorkLogResDTO);
            reportTotal += Double.parseDouble(txnWorkLogResDTO.getTotal());

        }
        try {
            reportResDTO.setCreator(user.getEmployee().getName());
            reportResDTO.setDate("" + currentDate);
            reportResDTO.setReportName("Site report: " + site.getName());
            reportResDTO.setLogs(logList);
            reportResDTO.setReportPdfName(report.getName());
            reportResDTO.setTotal(reportTotal);
            pdfGenerator.createPdf(reportResDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return report;
    }

    @Override
    public Report generateFarmLabourReport(Long farmId, String username) {

        List<TxnWorkLogResDTO> logList = new ArrayList<TxnWorkLogResDTO>();
        ReportResDTO reportResDTO = new ReportResDTO();
        TxnWorkLogResDTO txnWorkLogResDTO = new TxnWorkLogResDTO();

        User user = userService.findUserByUsername(username).get();
        Report report = new Report();
        LocalDateTime currentDate = LocalDateTime.now();
        Farm farm = farmService.findFarmById(farmId).get();

        int reportTotal = 0;

        report.setDate("" + currentDate);
        report.setName("farmLabour" + username + farm.getName());
        report.setLocation("./pdf/" + report.getName() + ".pdf");
        report.setUser(user);
        this.insertReport(report);
        for (TxnWorkLog txnWorkLog : txnWorkLogService.getTxnWorkLogByFarmId(farmId)) {
            txnWorkLogResDTO = new TxnWorkLogResDTO();

            txnWorkLogResDTO.setId(txnWorkLog.getId());
            txnWorkLogResDTO.setEmployeeName(txnWorkLog.getEmployee().getName());
            txnWorkLogResDTO.setEmployeeSurname(txnWorkLog.getEmployee().getSurname());
            txnWorkLogResDTO
                    .setHours("" + (txnWorkLog.getTotalHours() / 60) + "h" + (txnWorkLog.getTotalHours() % 60) + "min");
            txnWorkLogResDTO.setHourType(txnWorkLog.getHourType().getName());
            txnWorkLogResDTO.setTarif("" + txnWorkLog.getHourType().getHourlyRate());
            txnWorkLogResDTO.setTotal(calculateCost(txnWorkLog));
            logList.add(txnWorkLogResDTO);
            reportTotal += Double.parseDouble(txnWorkLogResDTO.getTotal());
        }
        try {
            reportResDTO.setCreator(user.getEmployee().getName());
            reportResDTO.setDate("" + currentDate);
            reportResDTO.setReportName("Farn report: " + farm.getName());
            reportResDTO.setLogs(logList);
            reportResDTO.setReportPdfName(report.getName());
            reportResDTO.setTotal(reportTotal);
            pdfGenerator.createPdf(reportResDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return report;
    }

    @Override
    public Report generateCropLabourReport(Long cropId, String username) {

        List<TxnWorkLogResDTO> logList = new ArrayList<TxnWorkLogResDTO>();
        ReportResDTO reportResDTO = new ReportResDTO();
        TxnWorkLogResDTO txnWorkLogResDTO = new TxnWorkLogResDTO();

        User user = userService.findUserByUsername(username).get();
        Report report = new Report();
        LocalDateTime currentDate = LocalDateTime.now();
        Crop crop = cropService.findCropById(cropId).get();

        int reportTotal = 0;

        report.setDate("" + currentDate);
        report.setName("cropLabour" + username + crop.getName());
        report.setLocation("./pdf/" + report.getName() + ".pdf");
        report.setUser(user);
        this.insertReport(report);
        for (TxnWorkLog txnWorkLog : txnWorkLogService.getTxnWorkLogByCropId(cropId)) {
            txnWorkLogResDTO = new TxnWorkLogResDTO();

            txnWorkLogResDTO.setId(txnWorkLog.getId());
            txnWorkLogResDTO.setEmployeeName(txnWorkLog.getEmployee().getName());
            txnWorkLogResDTO.setEmployeeSurname(txnWorkLog.getEmployee().getSurname());
            txnWorkLogResDTO
                    .setHours("" + (txnWorkLog.getTotalHours() / 60) + "h" + (txnWorkLog.getTotalHours() % 60) + "min");
            txnWorkLogResDTO.setHourType(txnWorkLog.getHourType().getName());
            txnWorkLogResDTO.setTarif("" + txnWorkLog.getHourType().getHourlyRate());
            txnWorkLogResDTO.setTotal(calculateCost(txnWorkLog));
            logList.add(txnWorkLogResDTO);
            reportTotal += Double.parseDouble(txnWorkLogResDTO.getTotal());
        }
        try

        {
            reportResDTO.setCreator(user.getEmployee().getName());
            reportResDTO.setDate("" + currentDate);
            reportResDTO.setReportName("Crop report: " + crop.getName());
            reportResDTO.setLogs(logList);
            reportResDTO.setReportPdfName(report.getName());
            reportResDTO.setTotal(reportTotal);
            pdfGenerator.createPdf(reportResDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return report;
    }

    public void insertReport(Report report) {
        reportRepo.save(report);
    }

    private String calculateCost(TxnWorkLog txnWorkLog) {

        int totalWorkedHours = txnWorkLog.getTotalHours();
        HourType hourType = txnWorkLog.getHourType();

        double hoursCost = (totalWorkedHours / 60) * hourType.getHourlyRate();
        double minitues = ((totalWorkedHours % 60) / 60 * 100) * hourType.getHourlyRate();

        return "" + (hoursCost + minitues);
    }
}