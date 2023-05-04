package com.example.vacationpay.service;

import com.example.vacationpay.service.impl.PaymentServiceImpl;
import com.example.vacationpay.service.impl.WorkDaysCountingServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentServiceTest {


    @Test
    void canCountSalaryWithOneVacationDay10k() {
        PaymentServiceImpl paymentServiceImlp = new PaymentServiceImpl(null);
        int salary = 10000;
        assertEquals(salary / (12 * 29.3) * 1, paymentServiceImlp.calculatePayment(salary, 1), 0.01);
    }

    @Test
    void canCountSalaryWithOneVacationDay20k() {
        PaymentServiceImpl paymentServiceImlp = new PaymentServiceImpl(null);
        int salary = 20000;
        assertEquals(salary / (12 * 29.3) * 1, paymentServiceImlp.calculatePayment(salary, 1), 0.01);
    }

    @Test
    void canCountSalaryWithFiveVacationDay10k() {
        PaymentServiceImpl paymentServiceImlp = new PaymentServiceImpl(null);
        int salary = 10000;
        assertEquals(salary / (12 * 29.3) * 5, paymentServiceImlp.calculatePayment(salary, 5), 0.01);
    }

    @Test
    void canCountSalaryCalendarWeek() {
        WorkDaysCountingServiceImpl countingService = Mockito.mock(WorkDaysCountingServiceImpl.class);
        LocalDate startDate =  LocalDate.of(2023, Month.MAY, 1);
        LocalDate endDate =  LocalDate.of(2023, Month.MAY, 8);
        when(countingService.countWorkDays(startDate, endDate)).thenReturn(5);
        PaymentServiceImpl paymentServiceImlp = new PaymentServiceImpl(countingService);
        int salary = 10000;
        assertEquals(salary / (12 * 29.3) * 5, paymentServiceImlp.calculateWithCalendar(salary, startDate, endDate), 0.01);
    }

    @Test
    void canCountSalaryCalendarZeroWorkingDays() {
        WorkDaysCountingServiceImpl countingService = Mockito.mock(WorkDaysCountingServiceImpl.class);
        LocalDate startDate =  LocalDate.of(2023, Month.MAY, 6);
        LocalDate endDate =  LocalDate.of(2023, Month.MAY, 7);
        when(countingService.countWorkDays(startDate, endDate)).thenReturn(0);
        PaymentServiceImpl paymentServiceImlp = new PaymentServiceImpl(countingService);
        int salary = 10000;
        assertEquals(0, paymentServiceImlp.calculateWithCalendar(salary, startDate, endDate), 0.01);
    }

    @Test
    void canCountSalaryCalendarOneWorkingDas() {
        WorkDaysCountingServiceImpl countingService = Mockito.mock(WorkDaysCountingServiceImpl.class);
        LocalDate startDate =  LocalDate.of(2023, Month.MAY, 1);
        LocalDate endDate =  LocalDate.of(2023, Month.MAY, 2);
        when(countingService.countWorkDays(startDate, endDate)).thenReturn(1);
        PaymentServiceImpl paymentServiceImlp = new PaymentServiceImpl(countingService);
        int salary = 10000;
        assertEquals(salary / (12 * 29.3) * 1, paymentServiceImlp.calculateWithCalendar(salary, startDate, endDate), 0.01);
    }
}
