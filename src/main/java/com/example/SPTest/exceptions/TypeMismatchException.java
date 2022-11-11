package com.example.SPTest.exceptions;

public class TypeMismatchException extends RuntimeException{
    public TypeMismatchException(String fieldName, String typeName){
        super("Field " + fieldName + " does not match type " + typeName);
    }
}
