package com.uniloftsky.springframework.taskmanager.data.services;

import com.uniloftsky.springframework.taskmanager.model.Task;

import java.util.Set;

public interface TaskService {

    Task findById(Long id);

    Set<Task> findAll();

    Task save(Task task);

    void delete(Task task);

}
