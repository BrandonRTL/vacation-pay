package com.example.vacationpay.controller;

import com.example.vacationpay.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentServiceImpl paymentServiceImlp;

    @Test
    void cantCalculatePaymentWithoutParams() throws Exception {
        mockMvc.perform(get("/calculate"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void canCalculatePaymentOneDay() throws Exception {
        double salary = 10000;
        double res = salary / (12 * 29.3) * 1;
        when(paymentServiceImlp.calculatePayment(salary, 1))
                .thenReturn(res);
        mockMvc.perform(get("/calculate?salary=10000&vacationDays=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$").value(closeTo(res, 0.001), Double.class));
    }

    @Test
    void canCalculatePaymentTwoWeeks() throws Exception {
        double salary = 10000;
        double res = salary / (12 * 29.3) * 14;
        when(paymentServiceImlp.calculatePayment(salary, 14))
                .thenReturn(res);
        mockMvc.perform(get("/calculate?salary=10000&vacationDays=14"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$").value(closeTo(res, 0.001), Double.class));
    }


    @Test
    void canCalculatePaymentCalendar() throws Exception {
        double salary = 10000;
        double res = salary / (12 * 29.3) * 5;
        LocalDate startDate = LocalDate.of(2023, Month.MAY, 1);
        LocalDate endDate = LocalDate.of(2023, Month.MAY, 8);
        when(paymentServiceImlp.calculateWithCalendar(salary, startDate, endDate))
                .thenReturn(res);
        mockMvc.perform(get("/calculateCalendar?salary=10000&startDate=2023-05-01&endDate=2023-05-08"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$").value(closeTo(res, 0.001), Double.class));
    }
}