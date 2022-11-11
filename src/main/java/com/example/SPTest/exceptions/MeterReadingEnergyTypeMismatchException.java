package com.example.SPTest.exceptions;

import com.example.SPTest.enums.EnergyType;

public class MeterReadingEnergyTypeMismatchException extends RuntimeException {
    public MeterReadingEnergyTypeMismatchException(EnergyType expectedEnergyType, Long id){
        super("Could not add reading to meter with ID " + id + ".  Expected energy type: " + expectedEnergyType);
    }

}
