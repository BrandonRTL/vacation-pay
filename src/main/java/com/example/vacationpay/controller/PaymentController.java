package com.example.vacationpay.controller;

import com.example.vacationpay.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello world!";
    }

    @GetMapping("/calculate")
    public double calculatePayment(@RequestParam double salary,
                                   @RequestParam int vacationDays) {
        return paymentService.calculatePayment(salary, vacationDays);
    }

    @GetMapping("/calculateCalendar")
    public double calculatePaymentCalendar(@RequestParam double salary,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   LocalDate startDate,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   LocalDate endDate) {
        return paymentService.calculateWithCalendar(salary, startDate, endDate);
    }

}
