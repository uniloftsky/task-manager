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

public final class MonthGenerator {

    private MonthGenerator() {
    }

    public static Month generateMonth(LocalDate localDate) {
        YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonth());
        return Month.builder()
                .daysCount(yearMonth.lengthOfMonth())
                .weeksCount(yearMonth.lengthOfMonth() / 7)
                .name(getMonthName(yearMonth))
                .nextMonth(localDate.plusMonths(1))
                .prevMonth(localDate.minusMonths(1))
                .days(fillMonthWithDays(yearMonth))
                .build();
    }

    private static Set<Day> fillMonthWithDays(YearMonth yearMonth) {
        Set<Day> days = new TreeSet<>();
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            days.add(Day.builder()
                    .dayDate(yearMonth.atDay(i))
                    .dayIndex(i)
                    .dayOfWeek(getDayOfWeek(yearMonth.atDay(i))).build());
        }
        return days;
    }

    public static DaysOfWeek getDayOfWeek(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.valueOf(localDate));
        int dayOFWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOFWeek) {
            case 1:
                return DaysOfWeek.SUNDAY;
            case 2:
                return DaysOfWeek.MONDAY;
            case 3:
                return DaysOfWeek.TUESDAY;
            case 4:
                return DaysOfWeek.WEDNESDAY;
            case 5:
                return DaysOfWeek.THURSDAY;
            case 6:
                return DaysOfWeek.FRIDAY;
            default:
                return DaysOfWeek.SATURDAY;
        }
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

}
