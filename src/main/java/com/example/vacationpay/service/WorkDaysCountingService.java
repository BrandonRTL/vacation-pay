package com.example.vacationpay.service;

import java.time.LocalDate;

public interface WorkDaysCountingService {


    int countWorkDays(LocalDate startDate, LocalDate endDate);
}
