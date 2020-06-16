package com.example.farmapp.Service;

import com.example.farmapp.Entity.Report;

public interface ReportService {

    public Report generateSiteLabourReport(Long siteId, String username);

    public Report generateFarmLabourReport(Long farmId, String username);

    public Report generateCropLabourReport(Long cropId, String username);

    public void insertReport(Report report);
}