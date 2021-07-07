package com.lhind.trip.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public String handleUserEmailException(SQLIntegrityConstraintViolationException exception, Model model){
        model.addAttribute("error", "User with this email already exists");

        //faqja html
        return "";
    }

    @ExceptionHandler(value = FlightException.class)
    public String handleWrongDatesOnCreateFlights(FlightException exception, Model model){
        model.addAttribute("error", exception.getMessage());
        return "";
    }
}
