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
    public Month getMonth(String date) {
        return generateMonth(LocalDate.parse(date));
    }

    @Override
    public Month getMonth(LocalDate date) {
        return generateMonth(date);
    }

    @Override
    public Set<Day> getMonthDays(Month month) {
        return null;
    }

    private Month generateMonth(LocalDate localDate) {
        YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonth());
        Month month = Month.builder()
                .daysCount(yearMonth.lengthOfMonth())
                .weeksCount(yearMonth.lengthOfMonth() / 7)
                .build();
        fillMonthWithDays(month, yearMonth);
        return month;
    }

    private void fillMonthWithDays(Month month, YearMonth yearMonth) {
        Set<Day> days = new TreeSet<>();
        for (int i = 1; i <= month.getDaysCount(); i++) {
            days.add(new Day(yearMonth.atDay(i), "Понеділок", i));
        }
        month.setDays(days);
    }
}
