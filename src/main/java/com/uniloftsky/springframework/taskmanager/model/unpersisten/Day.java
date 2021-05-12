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
public class Day {

    private LocalDate dayDate;
    private String name;

}
