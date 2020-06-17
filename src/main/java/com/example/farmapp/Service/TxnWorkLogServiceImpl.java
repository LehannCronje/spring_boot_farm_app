package com.example.farmapp.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.example.farmapp.Entity.Crop;
import com.example.farmapp.Entity.CropWork;
import com.example.farmapp.Entity.Employee;
import com.example.farmapp.Entity.Farm;
import com.example.farmapp.Entity.FarmSite;
import com.example.farmapp.Entity.HourType;
import com.example.farmapp.Entity.Site;
import com.example.farmapp.Entity.SiteCrop;
import com.example.farmapp.Entity.TxnWorkLog;
import com.example.farmapp.Entity.Work;
import com.example.farmapp.Repository.TxnWorkLogRepository;
import com.example.farmapp.dto.TxnWorkLogReqDTO;
import com.example.farmapp.dto.TxnWorkLogResDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TxnWorkLogServiceImpl implements TxnWorkLogService {

    @Autowired
    private TxnWorkLogRepository txnWorkLogRepo;

    @Autowired
    private CropWorkService cropWorkService;

    @Autowired
    private SiteCropService siteCropService;

    @Autowired
    private WorkService workService;

    @Autowired
    private HourTypeService hourTypeService;

    @Autowired
    private FarmEmployeeService farmEmployeeService;

    @Autowired
    private FarmSiteService farmSiteService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private FarmService farmService;

    @Autowired
    private CropService cropService;

    @Override
    public void createTxnWorkLog(TxnWorkLogReqDTO txnWorkLogReqDTO) {

        SiteCrop siteCrop = siteCropService
                .findByFarmSiteIdAndCropId(txnWorkLogReqDTO.getSiteId(), txnWorkLogReqDTO.getCropId()).get();
        Work work = workService.findWorkById(txnWorkLogReqDTO.getWorkId()).get();

        CropWork cropWork = cropWorkService
                .findCropWorkBySiteCropAndWork(siteCrop.getId(), txnWorkLogReqDTO.getWorkId()).orElse(null);

        Employee employee = farmEmployeeService.findFarmEmployeeById(txnWorkLogReqDTO.getFarmEmpId()).get()
                .getEmployee();

        int employeeWorkedHours = timeWorked(convertTime(txnWorkLogReqDTO.getStartTime()),
                convertTime(txnWorkLogReqDTO.getEndTime()));

        LocalDateTime timestamp = LocalDateTime.now();
        if (cropWork == null) {
            cropWork = new CropWork();
            cropWork.setSiteCrop(siteCrop);
            cropWork.setWork(work);
            cropWorkService.insertCropWork(cropWork);
        }

        if ((employee.getWorkedHours() + employeeWorkedHours) > 480 && employee.getWorkedHours() < 480) {
            int needed = 480 - employee.getWorkedHours();
            int overTimeHours = employeeWorkedHours - needed;
            TxnWorkLog normalTimeTxnWorkLog = new TxnWorkLog();
            TxnWorkLog overTimeTxnWorkLog = new TxnWorkLog();
            HourType normal = hourTypeService.findHourTypeByName("NORMAL").get();
            HourType overtime = hourTypeService.findHourTypeByName("OVERTIME").get();

            normalTimeTxnWorkLog.setCropWork(cropWork);
            normalTimeTxnWorkLog.setEmployee(employee);
            normalTimeTxnWorkLog.setHourType(normal);
            normalTimeTxnWorkLog.setTotalHours(needed);
            normalTimeTxnWorkLog.setTimestamp(timestamp);
            txnWorkLogRepo.save(normalTimeTxnWorkLog);

            overTimeTxnWorkLog.setCropWork(cropWork);
            overTimeTxnWorkLog.setEmployee(employee);
            overTimeTxnWorkLog.setHourType(overtime);
            overTimeTxnWorkLog.setTotalHours(overTimeHours);
            overTimeTxnWorkLog.setTimestamp(timestamp);
            txnWorkLogRepo.save(overTimeTxnWorkLog);
        } else {
            TxnWorkLog txnWorkLog = new TxnWorkLog();

            txnWorkLog.setCropWork(cropWork);
            txnWorkLog.setTotalHours(employeeWorkedHours);
            txnWorkLog.setHourType(hourTypeService.getHourType(employee));
            txnWorkLog.setEmployee(employee);
            txnWorkLog.setTimestamp(timestamp);
            txnWorkLogRepo.save(txnWorkLog);
        }

        employee.setWorkedHours(employee.getWorkedHours() + employeeWorkedHours);
        employeeService.insertEmployee(employee);
    }

    @Override
    public List<TxnWorkLogResDTO> getTxnWorkLogsByFarmSite(Long farmSiteId) {

        FarmSite farmSite = farmSiteService.findFarmSiteById(farmSiteId).get();

        return farmSite.getSiteCrop().stream().flatMap(siteCrop -> siteCrop.getCropWork().stream())
                .flatMap(cropWork -> cropWork.getTxnWorkLog().stream()).map(txnWorkLog -> {
                    TxnWorkLogResDTO txnDTO = new TxnWorkLogResDTO();
                    int timeInMin = txnWorkLog.getTotalHours();
                    txnDTO.setId(txnWorkLog.getId()); //builder
                    txnDTO.setEmployeeName(txnWorkLog.getEmployee().getName());
                    txnDTO.setWork(txnWorkLog.getCropWork().getWork().getName());
                    txnDTO.setTimeWorked("" + timeInMin / 60 + "h" + " " + timeInMin % 60 + "min");
                    return txnDTO;
                }).collect(Collectors.toList());
    }

    public String convertTime(String time) {
        // improve
        if (time.split(":")[0].length() == 1) {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern("h:mm a"))
                    .format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm a"))
                    .format(DateTimeFormatter.ofPattern("HH:mm"));
        }
    }

    public int timeWorked(String startTime, String endTime) {

        int starth = Integer.parseInt(startTime.split(":")[0]);
        int startm = Integer.parseInt(startTime.split(":")[1]);
        int endh = Integer.parseInt(endTime.split(":")[0]);
        int endm = Integer.parseInt(endTime.split(":")[1]);
        int totalTimemin = ((endh * 60 + endm) - (starth * 60 + startm));

        return totalTimemin;
    }

    @Override
    public List<TxnWorkLog> getTxnWorkLogBySiteId(Long siteId) {

        Site site = siteService.findSiteById(siteId);

        return site.getFarmSite().stream().flatMap(farmSite -> farmSite.getSiteCrop().stream())
                .flatMap(siteCrop -> siteCrop.getCropWork().stream())
                .flatMap(cropWork -> cropWork.getTxnWorkLog().stream()).collect(Collectors.toList());
    }

    @Override
    public List<TxnWorkLog> getTxnWorkLogByFarmId(Long farmId) {

        Farm farm = farmService.findFarmById(farmId).get();

        return farm.getFarmSite().stream().flatMap(farmSite -> farmSite.getSiteCrop().stream())
                .flatMap(siteCrop -> siteCrop.getCropWork().stream())
                .flatMap(cropWork -> cropWork.getTxnWorkLog().stream()).collect(Collectors.toList());

    }

    @Override
    public List<TxnWorkLog> getTxnWorkLogByCropId(Long cropId) {

        Crop crop = cropService.findCropById(cropId).get();

        return crop.getSiteCrop().stream().flatMap(siteCrop -> siteCrop.getCropWork().stream())
                .flatMap(cropWork -> cropWork.getTxnWorkLog().stream()).collect(Collectors.toList());
    }
}