package com.example.vacationpay.service;

import com.example.vacationpay.service.impl.WorkDaysCountingServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class WorkDaysCountTest {

    private static WorkDaysCountingServiceImpl countingService;

    @BeforeAll
    static void  init() {
        countingService = new WorkDaysCountingServiceImpl();
    }
    @Test
    void mondayShouldBeWorkDay() {
        LocalDate date =  LocalDate.of(2023, Month.MAY, 1);
        assertEquals(false, countingService.isNonWorkingDay(date));
    }

    @Test
    void tuesdayShouldBeWorkDay() {
        LocalDate date =  LocalDate.of(2023, Month.MAY, 2);
        assertEquals(false, countingService.isNonWorkingDay(date));
    }

    @Test
    void wednesdayShouldBeWorkDay() {
        LocalDate date =  LocalDate.of(2023, Month.MAY, 3);
        assertEquals(false, countingService.isNonWorkingDay(date));
    }

    @Test
    void thursdayShouldBeWorkDay() {
        LocalDate date =  LocalDate.of(2023, Month.MAY, 4);
        assertEquals(false, countingService.isNonWorkingDay(date));
    }

    @Test
    void fridayShouldBeWorkDay() {
        LocalDate date =  LocalDate.of(2023, Month.MAY, 5);
        assertEquals(false, countingService.isNonWorkingDay(date));
    }

    @Test
    void saturdayShouldNotBeWorkDay() {
        LocalDate date =  LocalDate.of(2023, Month.MAY, 6);
        assertEquals(true, countingService.isNonWorkingDay(date));
    }

    @Test
    void sundayShouldNotBeWorkDay() {
        LocalDate date =  LocalDate.of(2023, Month.MAY, 7);
        assertEquals(true, countingService.isNonWorkingDay(date));
    }

    @Test
    void oneWeekShouldHaveFiveWorkDays() {
        LocalDate startDate =  LocalDate.of(2023, Month.MAY, 1);
        LocalDate endDate =  LocalDate.of(2023, Month.MAY, 8);
        assertEquals(5, countingService.countWorkDays(startDate, endDate));
    }

    @Test
    void methodCanCountTwoWorkDays() {
        LocalDate startDate =  LocalDate.of(2023, Month.MAY, 4);
        LocalDate endDate =  LocalDate.of(2023, Month.MAY, 6);
        assertEquals(2, countingService.countWorkDays(startDate, endDate));
    }

    @Test
    void methodCanCountWorkDay() {
        LocalDate startDate =  LocalDate.of(2023, Month.MAY, 1);
        LocalDate endDate =  LocalDate.of(2023, Month.MAY, 2);
        assertEquals(1, countingService.countWorkDays(startDate, endDate));
    }

    @Test
    void methodCanCountDayOff() {
        LocalDate startDate =  LocalDate.of(2023, Month.MAY, 6);
        LocalDate endDate =  LocalDate.of(2023, Month.MAY, 7);
        assertEquals(0, countingService.countWorkDays(startDate, endDate));
    }

    @Test
    void canCountEndBeforeStart() {
        LocalDate startDate =  LocalDate.of(2023, Month.MAY, 8);
        LocalDate endDate =  LocalDate.of(2023, Month.MAY, 1);
        assertEquals(0, countingService.countWorkDays(startDate, endDate));
    }
}
