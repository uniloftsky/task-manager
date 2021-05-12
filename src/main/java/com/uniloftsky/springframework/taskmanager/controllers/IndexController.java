package com.uniloftsky.springframework.taskmanager.controllers;

import com.uniloftsky.springframework.taskmanager.data.services.MonthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class IndexController {

    private final MonthService monthService;

    public IndexController(MonthService monthService) {
        this.monthService = monthService;
    }

    @GetMapping("*")
    public String redirectToIndexPage() {
        return "redirect:/";
    }

    @GetMapping({"/index", "/"})
    public String getIndexPage(Model model) {
        model.addAttribute("month", monthService.getMonth(LocalDate.now()));
        return "index";
    }

    @GetMapping(value = "/", params = "date")
    public String getIndexPageWithDate(@RequestParam("date") String date, Model model) {
        model.addAttribute("month", monthService.getMonth(LocalDate.parse(date)));
        return "index";
    }

}
