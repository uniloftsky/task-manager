package com.uniloftsky.springframework.taskmanager.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalController {

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public String handleInvalidDate(Exception exception, Model model) {
        System.out.println("dsadsa");
        model.addAttribute("error", exception);
        return "datetimeparseerror";
    }

}
