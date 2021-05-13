package com.uniloftsky.springframework.taskmanager.generator;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Day;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;
import com.uniloftsky.springframework.taskmanager.util.calendar.generator.DaysGenerator;
import com.uniloftsky.springframework.taskmanager.util.calendar.generator.MonthGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Тестирование генератора заключается в том, что сгенерированный месяц должен иметь свои количества дней + дни хвостов.
 * Заготовленные дни хвостов реального месяца должны совпадать со сгенерированными.
 * Для тестирования были добавлены Mock-данные, а конкретно хвост дней с предыдущего месяца и хвост дней со следующего,
 * которые сравниваются с хвостами, которые генерирует класс DaysGenerator.
 */
public class MonthGeneratorTest {

    private Month month;
    private YearMonth ym;

    private int prevMonthDays;
    private int nextMonthDays;

    private final List<Day> prevMonthDaysList = new ArrayList<>();
    private final List<Day> nextMonthDaysList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        //Mock data
        LocalDate localDate = LocalDate.of(2021, 5, 20);
        prevMonthDays = 5;
        nextMonthDays = 6;
        fillPrevMonthDays();
        fillNextMonthDays();
        month = MonthGenerator.generateMonth(localDate);
        ym = YearMonth.of(localDate.getYear(), localDate.getMonth());
    }

    @Test
    void testMonthGenerator() {
        int daysInMonth = ym.lengthOfMonth() + prevMonthDays + nextMonthDays;
        assertEquals(daysInMonth, month.getDays().size());
    }

    @Test
    void testPrevMonthDaysGenerator() {
        assertTrue(month.getDays().subList(0, 5).containsAll(prevMonthDaysList));
    }

    @Test
    void testNextMonthDaysGenerator() {
        assertTrue(month.getDays().subList(month.getDaysCount(), month.getDays().size()).containsAll(nextMonthDaysList));
    }

    private void fillPrevMonthDays() {
        for (int i = 26; i <= 30; i++) {
            LocalDate localDate = LocalDate.of(2021, 4, i);
            prevMonthDaysList.add(Day.builder().dayDate(localDate).dayIndex(i).dayOfWeek(DaysGenerator.handleDayOfWeek(localDate)).build());
        }
    }

    private void fillNextMonthDays() {
        for (int i = 1; i <= 6; i++) {
            LocalDate localDate = LocalDate.of(2021, 6, i);
            nextMonthDaysList.add(Day.builder().dayDate(localDate).dayIndex(i).dayOfWeek(DaysGenerator.handleDayOfWeek(localDate)).build());
        }
    }
}
