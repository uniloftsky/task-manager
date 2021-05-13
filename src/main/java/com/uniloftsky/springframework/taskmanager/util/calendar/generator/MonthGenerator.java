package com.uniloftsky.springframework.taskmanager.util.calendar.generator;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Day;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.DaysOfWeek;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.MonthName;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public final class MonthGenerator {

    private MonthGenerator() {
    }

    public static Month generateMonth(LocalDate localDate) {
        YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonth());
        return buildMonth(yearMonth, localDate);
    }

    private static List<Day> fillMonthWithDays(YearMonth yearMonth) {
        List<Day> days = new ArrayList<>();
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            days.add(DaysGenerator.buildDay(yearMonth, i));
        }
        days.addAll(0, DaysGenerator.getEndingMonthDays(days, DaysOfWeek.MONDAY));
        days.addAll(days.size(), DaysGenerator.getEndingMonthDays(days, DaysOfWeek.SUNDAY));
        return days;
    }

    private static String getMonthName(YearMonth yearMonth) {
        String name = "";
        for (MonthName monthName : MonthName.values()) {
            if (yearMonth.getMonth().name().equals(monthName.name())) {
                name = monthName.getLabel();
            }
        }
        return name;
    }

    private static Month buildMonth(YearMonth yearMonth, LocalDate localDate) {
        return Month.builder()
                .daysCount(yearMonth.lengthOfMonth())
                .name(getMonthName(yearMonth))
                .nextMonth(localDate.plusMonths(1))
                .prevMonth(localDate.minusMonths(1))
                .days(fillMonthWithDays(yearMonth))
                .monthDate(localDate)
                .build();
    }

}