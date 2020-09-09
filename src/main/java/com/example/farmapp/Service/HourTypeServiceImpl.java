package com.example.farmapp.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.example.farmapp.Entity.Employee;
import com.example.farmapp.Entity.HourType;
import com.example.farmapp.Repository.HourTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HourTypeServiceImpl implements HourTypeService {

    @Autowired
    private HourTypeRepository hourTypeRepo;
    // day-month
    @Value("#{'${holidays}'.split(',')}")
    List<String> holidays;

    @Override
    public Optional<HourType> findHourTypeByName(String name) {
        return hourTypeRepo.findByName(name);
    }

    @Override
    public HourType getHourType(Employee employee) {

        String year = "" + Calendar.getInstance().get(Calendar.YEAR);

        for (String holiday : holidays) {
            String ddmmyy = holiday + "-" + year;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate currentDate = LocalDate.now();
            LocalDate holidayDate = LocalDate.parse(ddmmyy, formatter);
            if (currentDate.equals(holidayDate) || currentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                return this.findHourTypeByName("SPECIAL").get();
            }
        }

        if (employee.getWorkedHours() > 480) {
            return this.findHourTypeByName("OVERTIME").get();
        }
        return this.findHourTypeByName("NORMAL").get();
    }

}