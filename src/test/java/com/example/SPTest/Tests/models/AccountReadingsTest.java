package com.example.SPTest.Tests.models;

import com.example.SPTest.enums.EnergyType;
import com.example.SPTest.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountReadingsTest {

    private Account account;
    private Meter meter1;
    private Meter meter2;
    private Meter meter3;
    private LocalDate localDate1;
    private LocalDate localDate2;
    private Reading reading1;
    private Reading reading2;
    private Reading reading3;
    private Reading reading4;
    private Reading reading5;
    private AccountReadings accountReadings;
    private Long id;


    @BeforeEach
    public void before(){
        account = new Account();
        meter1 = new Meter(account, EnergyType.GAS);
        meter2 = new Meter(account, EnergyType.GAS);
        meter3 = new Meter(account, EnergyType.ELECTRICITY);
        localDate1 = LocalDate.of(2020, 12, 20);
        localDate2 = LocalDate.of(2021, 1, 21);
        reading1 = new Reading(meter1, 12345L, localDate1);
        reading2 = new Reading(meter1, 12485L, localDate2);
        reading3 = new Reading(meter2, 12741L, localDate1);
        reading4 = new Reading(meter3, 12946L, localDate1);
        reading5 = new Reading(meter3, 13015L, localDate2);

        meter1.addReading(reading1);
        meter1.addReading(reading2);

        meter2.addReading(reading3);

        meter3.addReading(reading4);
        meter3.addReading(reading5);

        account.addMeter(meter1);
        account.addMeter(meter2);
        account.addMeter(meter3);
        id = 123L;
        account.setId(id);

        accountReadings = new AccountReadings(account);
    }

    @Test
    public void canGetAllProperties(){
        assertEquals(id, accountReadings.getAccountId());
        assertEquals(reading1.getReading(), accountReadings.getGasReadings().get(0).getReading());
        assertEquals(reading2.getReading(), accountReadings.getGasReadings().get(1).getReading());
        assertEquals(reading3.getReading(), accountReadings.getGasReadings().get(2).getReading());
        assertEquals(reading4.getReading(), accountReadings.getElecReadings().get(0).getReading());
        assertEquals(reading5.getReading(), accountReadings.getElecReadings().get(1).getReading());
    }

    @Test
    public void canFindEnergyTypesForReadings(){
        accountReadings.findEnergyTypesForReadings();
        assertTrue(accountReadings.getGasReadings().get(0).hasEnergyType(EnergyType.GAS));
        assertTrue(accountReadings.getGasReadings().get(2).hasEnergyType(EnergyType.GAS));
        assertTrue(accountReadings.getElecReadings().get(0).hasEnergyType(EnergyType.ELECTRICITY));
        assertTrue(accountReadings.getElecReadings().get(1).hasEnergyType(EnergyType.ELECTRICITY));
    }

    @Test
    public void canGetAllReadingResponses(){
        assertEquals(reading1.getReading(), accountReadings.allReadingResponses().get(0).getReading());
        assertEquals(reading2.getReading(), accountReadings.allReadingResponses().get(1).getReading());
        assertEquals(reading3.getReading(), accountReadings.allReadingResponses().get(2).getReading());
        assertEquals(reading4.getReading(), accountReadings.allReadingResponses().get(3).getReading());
        assertEquals(reading5.getReading(), accountReadings.allReadingResponses().get(4).getReading());
    }
}
