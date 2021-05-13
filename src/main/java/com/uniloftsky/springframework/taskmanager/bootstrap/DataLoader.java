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
        task1.setDate(LocalDate.of(2021, 5, 14));
        task1.setTime(LocalTime.of(2, 30));
        task1.setDescription("Some task");

        Task task2 = new Task();
        task2.setDate(LocalDate.of(2021, 5, 15));
        task2.setTime(LocalTime.of(18, 0));
        task2.setDescription("Some task2");

        Task task3 = new Task();
        task3.setDate(LocalDate.of(2021, 5, 17));
        task3.setTime(LocalTime.of(15, 25));
        task3.setDescription("Some task3");

        Task task4 = new Task();
        task4.setDate(LocalDate.of(2021, 5, 17));
        task4.setTime(LocalTime.of(15, 25));
        task4.setDescription("Some task4");

        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);

    }
}
