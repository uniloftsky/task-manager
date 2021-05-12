package com.uniloftsky.springframework.taskmanager.util;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Day;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.DaysOfWeek;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.MonthName;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

public class MonthGenerator {

    public static Month generateMonth(LocalDate localDate) {
        YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonth());
        Month month = Month.builder()
                .daysCount(yearMonth.lengthOfMonth())
                .weeksCount(yearMonth.lengthOfMonth() / 7)
                .name(getMonthName(yearMonth))
                .build();
        fillMonthWithDays(month, yearMonth);
        return month;
    }

    private static void fillMonthWithDays(Month month, YearMonth yearMonth) {
        Set<Day> days = new TreeSet<>();
        for (int i = 1; i <= month.getDaysCount(); i++) {
            days.add(Day.builder()
                    .dayDate(yearMonth.atDay(i))
                    .dayIndex(i)
                    .dayOfWeek(getDayOfWeek(yearMonth.atDay(i))).build());
        }
        month.setDays(days);
    }

    public static DaysOfWeek getDayOfWeek(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.valueOf(localDate));
        int dayOFWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOFWeek) {
            case 1:
                return DaysOfWeek.MONDAY;
            case 2:
                return DaysOfWeek.TUESDAY;
            case 3:
                return DaysOfWeek.WEDNESDAY;
            case 4:
                return DaysOfWeek.THURSDAY;
            case 5:
                return DaysOfWeek.FRIDAY;
            case 6:
                return DaysOfWeek.SATURDAY;
            default:
                return DaysOfWeek.SUNDAY;
        }
    }

    private static String getMonthName(YearMonth yearMonth) {
        for (MonthName monthName : MonthName.values()) {
            if (yearMonth.getMonth().name().equals(monthName.name())) {
                return monthName.getLabel();
            }
        }
        return "";
    }

}
