package com.uniloftsky.springframework.taskmanager.generator;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Day;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.DaysOfWeek;
import com.uniloftsky.springframework.taskmanager.util.calendar.generator.DaysGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaysGeneratorTest {

    private LocalDate localDate;
    private int dayIndex;
    private Calendar calendar;


    @BeforeEach
    void setUp() {
        localDate = LocalDate.of(2021, 5, 20);
        dayIndex = 20;
        calendar = Calendar.getInstance();
    }

    @Test
    void testBuildDay() {
        YearMonth ym = YearMonth.of(localDate.getYear(), localDate.getMonth());
        Day day = DaysGenerator.buildDay(ym, dayIndex);
        assertEquals(day.getDayDate(), localDate);
    }

    @Test
    void testHandleDayOfWeek() {
        calendar.setTime(Date.valueOf(localDate));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        DaysOfWeek daysOfWeek = DaysGenerator.handleDayOfWeek(localDate);
        assertEquals(daysOfWeek.ordinal() + 1, dayOfWeek);
    }


}
