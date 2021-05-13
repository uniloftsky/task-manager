package com.uniloftsky.springframework.taskmanager.controllers;

import com.uniloftsky.springframework.taskmanager.data.services.TaskService;
import com.uniloftsky.springframework.taskmanager.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/postTask")
    public String createNewTask(@ModelAttribute Task task, @RequestParam(name = "dayDate") String date) {
        taskService.save(task, date);
        return "redirect:/day?date=" + task.getDate();
    }

}
