package com.example.vacationpay.service.impl;

import com.example.vacationpay.service.PaymentService;
import com.example.vacationpay.service.WorkDaysCountingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final WorkDaysCountingService countingService;

    @Override
    public double calculatePayment(double salary, int vacationDays) {
        return salary / (12 * 29.3) * vacationDays;
    }

    @Override
    public double calculateWithCalendar(double salary, LocalDate startDate, LocalDate endDate) {
        int workDays = countingService.countWorkDays(startDate, endDate);
        return salary / (12 * 29.3) * workDays;
    }
}
