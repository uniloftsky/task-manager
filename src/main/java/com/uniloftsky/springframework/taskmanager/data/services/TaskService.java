package com.uniloftsky.springframework.taskmanager.data.services;

import com.uniloftsky.springframework.taskmanager.model.Task;

import java.time.LocalDate;
import java.util.Set;

public interface TaskService {

    Task findById(Long id);

    Set<Task> findAll();

    Set<Task> findAllByTaskDate(LocalDate localDate);

    Task save(Task task);

    Task save(Task task, String date);

    void delete(Task task);

}
