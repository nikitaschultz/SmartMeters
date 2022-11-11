package com.example.SPTest.exceptions;

public class MeterNotFoundException extends RuntimeException{
    public MeterNotFoundException(Long id){
        super("Could not find meter with ID " + id);
    }
}
