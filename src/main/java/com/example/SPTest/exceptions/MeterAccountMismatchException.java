package com.example.SPTest.exceptions;

public class MeterAccountMismatchException extends RuntimeException{
    public MeterAccountMismatchException(Long meterId, Long accountId){
        super("Meter with id " + meterId + " does not match account with id " + accountId);
    }
}
