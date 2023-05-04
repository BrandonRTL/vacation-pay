package com.example.vacationpay.service.impl;

import com.example.vacationpay.service.WorkDaysCountingService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class WorkDaysCountingServiceImpl implements WorkDaysCountingService {

    public boolean isNonWorkingDay(LocalDate date) {
        return DayOfWeek.SATURDAY.equals(date.getDayOfWeek()) || DayOfWeek.SUNDAY.equals(date.getDayOfWeek());
    }

    @Override
    public int countWorkDays(LocalDate startDate, LocalDate endDate) {
        int counter = 0;
        if (!endDate.isAfter(startDate)) {
            return counter;
        }
        while(startDate.isBefore(endDate)) {
            if (!isNonWorkingDay(startDate)) {
                counter++;
            }
            startDate = startDate.plusDays(1);
        }
        return counter;
    }
}
