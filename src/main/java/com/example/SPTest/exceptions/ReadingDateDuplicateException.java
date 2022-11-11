package com.example.SPTest.exceptions;

import java.time.LocalDate;

public class ReadingDateDuplicateException extends RuntimeException {

    public ReadingDateDuplicateException(LocalDate date){
        super("Reading already exists for date " + date);
    }
}
