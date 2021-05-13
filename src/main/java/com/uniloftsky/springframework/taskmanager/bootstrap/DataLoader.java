package com.uniloftsky.springframework.taskmanager.bootstrap;

import com.uniloftsky.springframework.taskmanager.data.services.TaskService;
import com.uniloftsky.springframework.taskmanager.model.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;


@Component
public class DataLoader implements CommandLineRunner {

    private final TaskService taskService;

    public DataLoader(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {
        Task task1 = new Task();
        task1.setTaskDate(LocalDate.of(2021, 5, 14));
        task1.setTaskTime(LocalTime.of(2, 30));
        task1.setTaskDescription("Some task");

        Task task2 = new Task();
        task2.setTaskDate(LocalDate.of(2021, 5, 15));
        task2.setTaskTime(LocalTime.of(18, 0));
        task2.setTaskDescription("Some task2");

        Task task3 = new Task();
        task3.setTaskDate(LocalDate.of(2021, 5, 17));
        task3.setTaskTime(LocalTime.of(15, 25));
        task3.setTaskDescription("Some task3");

        Task task4 = new Task();
        task4.setTaskDate(LocalDate.of(2021, 5, 17));
        task4.setTaskTime(LocalTime.of(15, 25));
        task4.setTaskDescription("Some task4");

        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);

    }
}
