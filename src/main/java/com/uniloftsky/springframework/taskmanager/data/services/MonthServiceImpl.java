package com.uniloftsky.springframework.taskmanager.data.services;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Day;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;
import com.uniloftsky.springframework.taskmanager.util.MonthGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class MonthServiceImpl implements MonthService {

    @Override
    public Month getMonth(LocalDate date) {
        return MonthGenerator.generateMonth(date);
    }

    @Override
    public Set<Day> getMonthDays(Month month) {
        return month.getDays();
    }
}
