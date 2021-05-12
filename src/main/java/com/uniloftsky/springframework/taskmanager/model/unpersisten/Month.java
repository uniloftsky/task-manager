package com.uniloftsky.springframework.taskmanager.model.unpersisten;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.TreeSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Month {

    private int daysCount;
    private int weeksCount;
    private TreeSet<Day> days;
    private LocalDate monthDate;

}
