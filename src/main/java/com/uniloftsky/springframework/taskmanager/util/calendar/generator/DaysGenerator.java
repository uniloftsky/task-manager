package com.uniloftsky.springframework.taskmanager.util.calendar.generator;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Day;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.DaysOfWeek;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//Генератор дней для месяца
public final class DaysGenerator {

    private static final List<Day> tempDaysList = new ArrayList<>();

    private static final Calendar calendar = Calendar.getInstance();

    static {
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
    }

    private DaysGenerator() {
    }

    //Выбор заполнения хвоста предыдущего или следующего месяца в зависимоти от условия
    static List<Day> getEndingMonthDays(List<Day> days, DaysOfWeek daysOfWeek) {
        tempDaysList.clear();
        Day endingMonthDay = daysOfWeek.equals(DaysOfWeek.MONDAY) ? days.get(0) : days.get(days.size() - 1);
        if (!endingMonthDay.getDayOfWeek().equals(daysOfWeek)) {
            handleDaysListForTailMonth(endingMonthDay, daysOfWeek);
        }
        return tempDaysList;
    }

    //Заполнение буферного списка днями с хвоста предыдущего или следующего месяца в зависимости от условия
    private static void handleDaysListForTailMonth(Day endingDay, DaysOfWeek daysOfWeek) {
        int monthDays = calculateMonthDays(endingDay, daysOfWeek);
        LocalDate localDate = endingDay.getDayDate();
        YearMonth ym = calculateYM(localDate, daysOfWeek);
        fillMonthTail(daysOfWeek, ym, monthDays);
    }

    //Вычисление значения переменной YearMonth в зависимости от условия
    private static YearMonth calculateYM(LocalDate localDate, DaysOfWeek daysOfWeek) {
        tempDaysList.clear();
        if (daysOfWeek.equals(DaysOfWeek.MONDAY)) {
            localDate = localDate.minusMonths(1);
        } else {
            localDate = localDate.plusMonths(1);
        }
        return YearMonth.of(localDate.getYear(), localDate.getMonth());
    }

    //Вычисление количества дней хвоста в зависимости от условия
    private static int calculateMonthDays(Day endingDay, DaysOfWeek daysOfWeek) {
        calendar.setTime(Date.valueOf(endingDay.getDayDate()));
        if (daysOfWeek.equals(DaysOfWeek.MONDAY)) {
            return endingDay.getDayOfWeek().ordinal() + 1 == 1 ? 6 : calendar.get(Calendar.DAY_OF_WEEK) - 2;
        } else {
            return 8 - calendar.get(Calendar.DAY_OF_WEEK);
        }
    }

    //Запуск цикла по заполнению буферного списка
    private static void fillMonthTail(DaysOfWeek daysOfWeek, YearMonth ym, int monthDays) {
        if (daysOfWeek.equals(DaysOfWeek.MONDAY)) {
            for (int i = ym.lengthOfMonth(); i > ym.lengthOfMonth() - monthDays; i--) {
                tempDaysList.add(buildDay(ym, i));
            }
            tempDaysList.sort(Day::compareTo);
        } else {
            for (int i = 1; i <= monthDays; i++) {
                tempDaysList.add(buildDay(ym, i));
            }
        }
    }

    //Обработка для указывания названия дня недели
    public static DaysOfWeek handleDayOfWeek(LocalDate localDate) {
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

    //Конструктор дня
    static Day buildDay(YearMonth yearMonth, int index) {
        return Day.builder()
                .dayDate(yearMonth.atDay(index))
                .dayIndex(index)
                .dayOfWeek(handleDayOfWeek(yearMonth.atDay(index)))
                .build();
    }

    //Конструктор дня с использованием даты
    public static Day buildDay(LocalDate localDate) {
        calendar.setTime(Date.valueOf(localDate));
        return Day.builder()
                .dayDate(localDate)
                .dayIndex(calendar.get(Calendar.DAY_OF_MONTH))
                .dayOfWeek(handleDayOfWeek(localDate))
                .build();
    }

}
