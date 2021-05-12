package com.uniloftsky.springframework.taskmanager.data.services;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Day;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;
import java.util.TreeSet;

@Service
public class MonthServiceImpl implements MonthService {

    @Override
    public Month getMonth(LocalDate date) {
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        Month month = new Month();
        month.setDaysCount(yearMonth.lengthOfMonth());
        month.setWeeksCount(yearMonth.lengthOfMonth() / 7);
        Set<Day> days = new TreeSet<>();
        for (int i = 1; i <= month.getDaysCount(); i++) {
            days.add(new Day(yearMonth.atDay(i), "Понеділок", i));
        }
        month.setDays(days);
        return month;
    }

    @Override
    public Set<Day> getMonthDays(Month month) {
        return null;
    }
}
