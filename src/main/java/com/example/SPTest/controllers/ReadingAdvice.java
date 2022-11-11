package com.example.SPTest.controllers;

import com.example.SPTest.exceptions.TypeMismatchException;
import com.example.SPTest.exceptions.MissingFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReadingAdvice {

    @ResponseBody
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String DateInvalidHandler(TypeMismatchException ex) { return ex.getMessage(); }

    @ResponseBody
    @ExceptionHandler(MissingFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String MissingFieldHandler(MissingFieldException ex) { return ex.getMessage(); }
}
