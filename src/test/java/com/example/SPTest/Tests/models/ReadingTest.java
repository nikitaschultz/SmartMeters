package com.example.SPTest.Tests.models;

import com.example.SPTest.enums.EnergyType;
import com.example.SPTest.models.Account;
import com.example.SPTest.models.Meter;
import com.example.SPTest.models.Reading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingTest {

    private Account account;
    private Meter meter1;
    private LocalDate localDate1;
    private LocalDate localDate2;
    private LocalDate localDate3;
    private Reading reading1;
    private Reading reading2;
    private Reading reading3;

    @BeforeEach
    public void before(){
        account = new Account();
        meter1 = new Meter(account, EnergyType.GAS);
        meter1 = new Meter(account, EnergyType.ELECTRICITY);
        localDate1 = LocalDate.of(2020, 12, 20);
        localDate2 = LocalDate.of(2021, 1, 21);
        localDate3 = LocalDate.of(2021, 2, 19);
        reading1 = new Reading(meter1, 12345L, localDate1);
        reading2 = new Reading(meter1, 12485L, localDate2);
        reading3 = new Reading(meter1, 12741L, localDate3);
    }

    @Test
    public void canGetProperties(){
        Long id = 123L;
        reading1.setId(id);

        assertEquals(meter1, reading1.getMeter());
        assertEquals(12345L, reading1.getReading());
        assertEquals(localDate1, reading1.getDate());
        assertEquals(id, reading1.getId());
    }

    @Test
    public void canGetStatsSinceLastRead(){
        meter1.addReading(reading1);
        meter1.addReading(reading2);
        reading2.setMeter(meter1);

        assertEquals(140L, reading2.statsSinceLastRead().get("usage"));
        assertEquals(32L, reading2.statsSinceLastRead().get("period"));

        meter1.addReading(reading3);
        reading2.setMeter(meter1);
        assertEquals(256L, reading3.statsSinceLastRead().get("usage"));
        assertEquals(29L, reading3.statsSinceLastRead().get("period"));
    }

    @Test
    public void returnsNullIfFirstRead(){
        assertEquals(null, reading1.statsSinceLastRead());
    }
}
