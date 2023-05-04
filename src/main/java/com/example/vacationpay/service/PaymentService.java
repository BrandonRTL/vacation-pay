package com.example.vacationpay.service;

import java.time.LocalDate;

public interface PaymentService {

    double calculatePayment(double salary, int vacationDays);

    double calculateWithCalendar(double salary, LocalDate startDate, LocalDate endDate);
}
