package com.uniloftsky.springframework.taskmanager.model.unpersisten;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Day implements Comparable {

    private LocalDate dayDate;
    private String name;
    private int dayIndex;

    @Override
    public int compareTo(Object o) {
        Day day = (Day) o;
        if (dayIndex > day.dayIndex) {
            return 1;
        } else {
            return -1;
        }
    }
}
