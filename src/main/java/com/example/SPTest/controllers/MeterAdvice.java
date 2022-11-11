package com.example.SPTest.controllers;

import com.example.SPTest.exceptions.MeterAccountMismatchException;
import com.example.SPTest.exceptions.MeterNotFoundException;
import com.example.SPTest.exceptions.MeterReadingEnergyTypeMismatchException;
import com.example.SPTest.exceptions.ReadingDateDuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MeterAdvice {

    @ResponseBody
    @ExceptionHandler(MeterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String meterNotFoundHandler(MeterNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MeterReadingEnergyTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String meterReadingEnergyTypeMismatchHandler(MeterReadingEnergyTypeMismatchException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ReadingDateDuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String ReadingDateDuplicateHandler(ReadingDateDuplicateException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MeterAccountMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String MeterAccountMismatchHandler(MeterAccountMismatchException ex) {
        return ex.getMessage();
    }
}
