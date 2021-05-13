package com.uniloftsky.springframework.taskmanager.data.services;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.Month;

import java.time.LocalDate;

public interface MonthService {

    Month getMonth(LocalDate date);

}
