package com.uniloftsky.springframework.taskmanager.controllers;

import com.uniloftsky.springframework.taskmanager.data.services.TaskService;
import com.uniloftsky.springframework.taskmanager.model.Task;
import com.uniloftsky.springframework.taskmanager.util.calendar.generator.DaysGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;


@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/postTask")
    public String createNewTask(@Valid @ModelAttribute("task") Task task, BindingResult result, @RequestParam(name = "dayDate") String date, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("task", task);
            model.addAttribute("tasks", taskService.findAllByTaskDate(LocalDate.parse(date)));
            model.addAttribute("day", DaysGenerator.buildDay(LocalDate.parse(date)));
            return "pages/day";
        }
        taskService.save(task, date);
        return "redirect:/day?date=" + task.getDate();
    }

}
