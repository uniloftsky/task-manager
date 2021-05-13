package com.uniloftsky.springframework.taskmanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.format.DateTimeParseException;

//Контроллер для обработки исключений и вывода глобальных ModelAttribute
@ControllerAdvice
public class GlobalController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public String handleInvalidDate(Exception exception, Model model) {
        model.addAttribute("error", exception);
        return "pages/dateparseerror";
    }

}
