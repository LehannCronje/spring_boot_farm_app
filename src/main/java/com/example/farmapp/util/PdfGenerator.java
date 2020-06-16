package com.example.farmapp.util;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.farmapp.Entity.Report;
import com.example.farmapp.dto.ReportResDTO;

public interface PdfGenerator {

    public void createPdf(ReportResDTO reportResDTO) throws Exception;

    public void downloadPdf(HttpServletResponse response, List<Report> reportList);

}