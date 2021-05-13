package com.uniloftsky.springframework.taskmanager.data.services;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;
import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.DaysOfWeek;
import com.uniloftsky.springframework.taskmanager.util.DaysGenerator;
import com.uniloftsky.springframework.taskmanager.util.MonthGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MonthServiceImpl implements MonthService {

    @Override
    public Month getMonth(LocalDate date) {
        return MonthGenerator.generateMonth(date);
    }

    @Override
    public DaysOfWeek getDayOfWeekByDate(LocalDate date) {
        return DaysGenerator.handleDayOfWeek(date);
    }
}
