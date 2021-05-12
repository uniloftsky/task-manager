package com.uniloftsky.springframework.taskmanager.data.services;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Day;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;

import java.time.LocalDate;
import java.util.Set;

public interface MonthService {

    Month getMonth(String date);
    Month getMonth(LocalDate date);
    Set<Day> getMonthDays(Month month);

}
