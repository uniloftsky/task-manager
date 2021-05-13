package com.uniloftsky.springframework.taskmanager.model.unpersisten;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.DaysOfWeek;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Day implements Comparable<Day> {

    private LocalDate dayDate;
    private DaysOfWeek dayOfWeek;
    private int dayIndex;

    @Override
    public int compareTo(Day day) {
        return dayIndex > day.dayIndex ? 1 : -1;
    }
}
