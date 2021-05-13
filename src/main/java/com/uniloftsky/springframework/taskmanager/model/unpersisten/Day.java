package com.uniloftsky.springframework.taskmanager.model.unpersisten;

import com.uniloftsky.springframework.taskmanager.model.unpersisten.enums.DaysOfWeek;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return dayIndex == day.dayIndex &&
                Objects.equals(dayDate, day.dayDate) &&
                dayOfWeek == day.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayDate, dayOfWeek, dayIndex);
    }
}
