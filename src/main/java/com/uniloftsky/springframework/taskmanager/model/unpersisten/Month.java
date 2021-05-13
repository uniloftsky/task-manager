package com.uniloftsky.springframework.taskmanager.model.unpersisten;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Month {

    private int daysCount;
    private List<Day> days;
    private LocalDate monthDate;
    private LocalDate nextMonth;
    private LocalDate prevMonth;
    private String name;

}
