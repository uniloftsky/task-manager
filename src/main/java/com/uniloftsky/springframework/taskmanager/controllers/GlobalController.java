package com.uniloftsky.springframework.taskmanager.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.zip.DataFormatException;

@ControllerAdvice
public class GlobalController {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public String handleInvalidDate(Exception exception, Model model) {
        model.addAttribute("error", exception);
        return "error";
    }

}
