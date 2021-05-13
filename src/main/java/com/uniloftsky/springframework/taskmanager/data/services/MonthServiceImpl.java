package com.uniloftsky.springframework.taskmanager.data.services;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;
import com.uniloftsky.springframework.taskmanager.util.calendar.generator.MonthGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MonthServiceImpl implements MonthService {

    @Override
    public Month getMonth(LocalDate date) {
        return MonthGenerator.generateMonth(date);
    }

}
