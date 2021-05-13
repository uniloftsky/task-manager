package com.uniloftsky.springframework.taskmanager.data.services;

import com.uniloftsky.springframework.taskmanager.data.repositories.TaskRepository;
import com.uniloftsky.springframework.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new RuntimeException("Expected task not found!");
        }
        return taskOptional.get();
    }

    @Override
    public Set<Task> findAll() {
        Set<Task> tasks = new HashSet<>();
        taskRepository.findAll().iterator().forEachRemaining(tasks::add);
        return tasks;
    }

    @Override
    public Set<Task> findAllByTaskDate(LocalDate localDate) {
        Set<Task> tasks = new TreeSet<>(Comparator.comparing(Task::getTime));
        taskRepository.findAllByDate(localDate).iterator().forEachRemaining(tasks::add);
        return tasks;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task save(Task task, String date) {
        task.setDate(LocalDate.parse(date));
        return taskRepository.save(task);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }
}
