package com.uniloftsky.springframework.taskmanager.controllers;

import com.uniloftsky.springframework.taskmanager.data.services.MonthService;
import com.uniloftsky.springframework.taskmanager.data.services.TaskService;
import com.uniloftsky.springframework.taskmanager.model.Task;
import com.uniloftsky.springframework.taskmanager.util.calendar.generator.DaysGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

//Контролер, отвечающий за обработку запросов на главную страницу и страницу отдельного дня
@Controller
public class IndexController {

    private final MonthService monthService;
    private final TaskService taskService;

    public IndexController(MonthService monthService, TaskService taskService) {
        this.monthService = monthService;
        this.taskService = taskService;
    }

    @GetMapping("*")
    public String redirectToIndexPage() {
        return "redirect:/";
    }

    @GetMapping({"/index", "/"})
    public String getIndexPage(Model model) {
        model.addAttribute("month", monthService.getMonth(LocalDate.now()));
        model.addAttribute("tasks", taskService);
        return "pages/index";
    }

    @GetMapping(value = "/", params = "date")
    public String getIndexPageWithDate(@RequestParam("date") String date, Model model) {
        model.addAttribute("month", monthService.getMonth(LocalDate.parse(date)));
        model.addAttribute("tasks", taskService);
        return "pages/index";
    }

    @GetMapping(value = "/day", params = "date")
    public String getSpecifiedDayPage(@RequestParam("date") String date, Model model) {
        model.addAttribute("tasks", taskService.findAllByTaskDate(LocalDate.parse(date)));
        model.addAttribute("day", DaysGenerator.buildDay(LocalDate.parse(date)));
        model.addAttribute("task", new Task());
        return "pages/day";
    }

}
