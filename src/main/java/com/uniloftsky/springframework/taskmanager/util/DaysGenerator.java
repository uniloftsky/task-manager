package com.uniloftsky.springframework.taskmanager.util;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Day;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.DaysOfWeek;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class DaysGenerator {

    private static final List<Day> tempDaysList = new ArrayList<>();

    private static final Calendar calendar = Calendar.getInstance();

    static {
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
    }

    private DaysGenerator() {
    }

    static List<Day> getEndingMonthDays(List<Day> days, DaysOfWeek daysOfWeek) {
        tempDaysList.clear();
        Day endingMonthDay = daysOfWeek.equals(DaysOfWeek.MONDAY) ? days.get(0) : days.get(days.size() - 1);
        if (!endingMonthDay.getDayOfWeek().equals(daysOfWeek)) {
            completeDaysList(daysOfWeek, days);
        }
        return tempDaysList;
    }

    private static void completeDaysList(DaysOfWeek dayOfWeek, List<Day> days) {
        tempDaysList.clear();
        Day endingDay = dayOfWeek.equals(DaysOfWeek.MONDAY) ? days.get(0) : days.get(days.size() - 1);
        calendar.setTime(Date.valueOf(endingDay.getDayDate()));
        int dayOfWeekIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek.equals(DaysOfWeek.MONDAY)) {
            int prevMonthDays = dayOfWeekIndex == 1 ? 6 : calendar.get(Calendar.DAY_OF_WEEK) - 2;
            LocalDate prevLocalDate = endingDay.getDayDate().minusMonths(1);
            YearMonth ym = YearMonth.of(prevLocalDate.getYear(), prevLocalDate.getMonth());
            for (int i = ym.lengthOfMonth(); i > ym.lengthOfMonth() - prevMonthDays; i--) {
                tempDaysList.add(buildDay(ym, i));
            }
            tempDaysList.sort(Day::compareTo);
        } else {
            int nextMonthDays = 8 - calendar.get(Calendar.DAY_OF_WEEK);
            LocalDate nextLocalDate = endingDay.getDayDate().plusMonths(1);
            YearMonth ym = YearMonth.of(nextLocalDate.getYear(), nextLocalDate.getMonth());
            for (int i = 1; i <= nextMonthDays; i++) {
                tempDaysList.add(buildDay(ym, i));
            }
        }
    }

    private static DaysOfWeek handleDayOfWeek(LocalDate localDate) {
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

    static Day buildDay(YearMonth yearMonth, int index) {
        return Day.builder()
                .dayDate(yearMonth.atDay(index))
                .dayIndex(index)
                .dayOfWeek(handleDayOfWeek(yearMonth.atDay(index)))
                .build();
    }

}
