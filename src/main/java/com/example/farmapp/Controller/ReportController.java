package com.example.farmapp.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.farmapp.Entity.Report;
import com.example.farmapp.Service.ReportService;
import com.example.farmapp.util.PdfGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping(value = "/site-report/{siteId}")
    public void generateSiteReport(@PathVariable Long siteId, @AuthenticationPrincipal UserDetails userDetails,
            HttpServletResponse response) {

        List<Report> reportList = new ArrayList<Report>();
        reportList.add(reportService.generateSiteLabourReport(siteId, userDetails.getUsername()));

        pdfGenerator.downloadPdf(response, reportList);
    }

    @GetMapping(value = "/farm-report/{farmId}")
    public void generateFarmReport(@PathVariable Long farmId, @AuthenticationPrincipal UserDetails userDetails,
            HttpServletResponse response) {

        List<Report> reportList = new ArrayList<Report>();
        reportList.add(reportService.generateFarmLabourReport(farmId, userDetails.getUsername()));

        pdfGenerator.downloadPdf(response, reportList);
    }

    @GetMapping(value = "/crop-report/{cropId}")
    public void generateCropReport(@PathVariable Long cropId, @AuthenticationPrincipal UserDetails userDetails,
            HttpServletResponse response) {

        List<Report> reportList = new ArrayList<Report>();
        reportList.add(reportService.generateCropLabourReport(cropId, userDetails.getUsername()));

        pdfGenerator.downloadPdf(response, reportList);
    }
}