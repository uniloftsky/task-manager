package com.uniloftsky.springframework.taskmanager.util;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Day;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.DaysOfWeek;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.MonthName;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class MonthGenerator {

    private static final Calendar calendar = Calendar.getInstance();

    private MonthGenerator() {
    }

    public static Month generateMonth(LocalDate localDate) {
        YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonth());
        return Month.builder()
                .daysCount(yearMonth.lengthOfMonth())
                .name(getMonthName(yearMonth))
                .nextMonth(localDate.plusMonths(1))
                .prevMonth(localDate.minusMonths(1))
                .days(fillMonthWithDays(yearMonth))
                .build();
    }

    private static List<Day> fillMonthWithDays(YearMonth yearMonth) {
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        List<Day> days = new ArrayList<>();
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            days.add(Day.builder()
                    .dayDate(yearMonth.atDay(i))
                    .dayIndex(i)
                    .dayOfWeek(getDayOfWeek(yearMonth.atDay(i))).build());
        }
        days.addAll(0, getPrevMonthDays(days));
        days.addAll(days.size(), getNextMonthDays(days));
        return days;
    }

    private static DaysOfWeek getDayOfWeek(LocalDate localDate) {
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(Date.valueOf(localDate));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
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

    private static List<Day> getNextMonthDays(List<Day> days) {
        int nextMonthDays;
        List<Day> nextMonthDaysList = new ArrayList<>();
        Day lastMonthDay = days.get(days.size() - 1);
        if (!lastMonthDay.getDayOfWeek().equals(DaysOfWeek.SUNDAY)) {
            calendar.setTime(Date.valueOf(lastMonthDay.getDayDate()));
            nextMonthDays = 8 - calendar.get(Calendar.DAY_OF_WEEK);
            LocalDate nextLocalDate = lastMonthDay.getDayDate().plusMonths(1);
            YearMonth ym = YearMonth.of(nextLocalDate.getYear(), nextLocalDate.getMonth());
            for (int i = 1; i <= nextMonthDays; i++) {
                nextMonthDaysList.add(Day.builder()
                        .dayDate(ym.atDay(i))
                        .dayIndex(i)
                        .dayOfWeek(getDayOfWeek(ym.atDay(i)))
                        .build());
            }
        }
        return nextMonthDaysList;
    }

    private static List<Day> getPrevMonthDays(List<Day> days) {
        int prevMonthDays;
        List<Day> prevMonthDaysList = new ArrayList<>();
        Day firstMonthDay = days.get(0);
        if (!firstMonthDay.getDayOfWeek().equals(DaysOfWeek.MONDAY)) {
            calendar.setTime(Date.valueOf(firstMonthDay.getDayDate()));
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == 1) {
                prevMonthDays = 6;
            } else {
                prevMonthDays = calendar.get(Calendar.DAY_OF_WEEK) - 2;
            }
            LocalDate prevLocalDate = firstMonthDay.getDayDate().minusMonths(1);
            YearMonth ym = YearMonth.of(prevLocalDate.getYear(), prevLocalDate.getMonth());
            for (int i = ym.lengthOfMonth(); i > ym.lengthOfMonth() - prevMonthDays; i--) {
                prevMonthDaysList.add(Day.builder()
                        .dayDate(ym.atDay(i))
                        .dayIndex(i)
                        .dayOfWeek(getDayOfWeek(ym.atDay(i)))
                        .build());
            }
        }
        prevMonthDaysList.sort(Day::compareTo);
        return prevMonthDaysList;
    }

}
