package com.example.SPTest.exceptions;

public class MissingFieldException extends RuntimeException {

    public MissingFieldException(String fieldName){
        super("Missing field " + fieldName);
    }

}
