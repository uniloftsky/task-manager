package com.uniloftsky.springframework.taskmanager.controllers;

import com.uniloftsky.springframework.taskmanager.data.services.TaskService;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }



}
