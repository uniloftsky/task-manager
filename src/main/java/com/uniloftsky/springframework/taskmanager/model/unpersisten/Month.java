package com.uniloftsky.springframework.taskmanager.model.unpersisten;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Month {

    private Set<Day> lastMonthDays;
    private Set<Day> nextMonthDays;
    private int daysCount;
    private int weeksCount;
    private Set<Day> days;
    private LocalDate monthDate;
    private LocalDate nextMonth;
    private LocalDate prevMonth;
    private String name;

}
