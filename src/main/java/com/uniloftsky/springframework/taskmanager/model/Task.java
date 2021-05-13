package com.uniloftsky.springframework.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task extends BaseEntity {

    @NotBlank(message = "Поле описания задачи не может быть пустым!")
    @Size(min = 5, max = 60, message = "Поле описания должно быть меньше 60 и больше 5")
    private String description;

    @NotNull(message = "Поле времени не может быть пустым!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

}
