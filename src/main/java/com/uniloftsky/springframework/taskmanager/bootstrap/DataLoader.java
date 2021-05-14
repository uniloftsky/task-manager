package com.uniloftsky.springframework.taskmanager.bootstrap;

import com.uniloftsky.springframework.taskmanager.data.repositories.TaskRepository;
import com.uniloftsky.springframework.taskmanager.model.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

//Класс, отвечающий за загрузку заготовленных данных в базу
@Component
public class DataLoader implements CommandLineRunner {

    private final TaskRepository taskRepository;

    public DataLoader(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Set<Task> tasks = new HashSet<>();
        tasks.add(new Task("Some task", LocalTime.of(2, 30), LocalDate.of(2021, 5, 14)));
        tasks.add(new Task("Some task 2", LocalTime.of(18, 0), LocalDate.of(2021, 5, 15)));
        tasks.add(new Task("Some task 3", LocalTime.of(15, 25), LocalDate.of(2021, 5, 17)));
        tasks.add(new Task("Some task 4", LocalTime.of(15, 25), LocalDate.of(2021, 5, 17)));

        System.out.println(System.getProperty("user.dir"));

        taskRepository.saveAll(tasks);
    }
}
