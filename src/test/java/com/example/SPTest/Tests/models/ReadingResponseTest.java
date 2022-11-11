package com.example.SPTest.Tests.models;

import com.example.SPTest.enums.EnergyType;
import com.example.SPTest.models.Account;
import com.example.SPTest.models.Meter;
import com.example.SPTest.models.Reading;
import com.example.SPTest.models.ReadingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReadingResponseTest {

    private Account account;
    private Meter meter1;
    private LocalDate localDate1;
    private LocalDate localDate2;
    private Reading reading1;
    private Reading reading2;
    private Long id;
    private Long id2;
    private ReadingResponse readingResponse;


    @BeforeEach
    public void before(){
        account = new Account();
        meter1 = new Meter(account, EnergyType.GAS);
        meter1 = new Meter(account, EnergyType.ELECTRICITY);
        localDate1 = LocalDate.of(2020, 12, 20);
        localDate2 = LocalDate.of(2021, 1, 21);
        reading1 = new Reading(meter1, 12345L, localDate1);
        reading2 = new Reading(meter1, 12485L, localDate2);

        meter1.addReading(reading1);
        meter1.addReading(reading2);
        id = 397L;
        meter1.setId(id);
        reading2.setMeter(meter1);
        id2 = 123L;
        reading2.setId(id2);
        readingResponse = new ReadingResponse(reading2);
    }

    @Test
    public void canGetAllProperties(){
        assertEquals(id2, readingResponse.getId());
        assertEquals(id, readingResponse.getMeterId());
        assertEquals(12485L, readingResponse.getReading());
        assertEquals(localDate2, readingResponse.getDate());
        assertEquals(140L, readingResponse.getUsageSinceLastRead());
        assertEquals(32L, readingResponse.getPeriodSinceLastRead());
        assertEquals(4.375d, readingResponse.getAvgDailyUsage());
    }

    @Test
    public void canMatchEnergyType(){
        readingResponse.setEnergyType(EnergyType.ELECTRICITY);
        assertTrue(readingResponse.hasEnergyType(EnergyType.ELECTRICITY));
        assertFalse(readingResponse.hasEnergyType(EnergyType.GAS));

        readingResponse.setEnergyType(EnergyType.GAS);
        assertTrue(readingResponse.hasEnergyType(EnergyType.GAS));
        assertFalse(readingResponse.hasEnergyType(EnergyType.ELECTRICITY));
    }


}
