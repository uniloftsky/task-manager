package com.uniloftsky.springframework.taskmanager.data.services;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.DaysOfWeek;

import java.time.LocalDate;

public interface MonthService {

    Month getMonth(LocalDate date);
    DaysOfWeek getDayOfWeekByDate(LocalDate date);
//    Set<Day> getMonthDays(Month month);

}
