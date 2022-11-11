package com.example.SPTest.Tests.models;

import com.example.SPTest.enums.EnergyType;
import com.example.SPTest.models.Account;
import com.example.SPTest.models.Meter;
import com.example.SPTest.models.Reading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeterTest {

    private Account account;
    private Meter meter;
    private LocalDate localDate1;
    private LocalDate localDate2;
    private LocalDate localDate3;
    private LocalDate localDate4;
    private LocalDate localDate5;
    private Reading reading1;
    private Reading reading2;
    private Reading reading3;
    private Reading reading4;
    private Reading reading5;


    @BeforeEach
    public void before(){
        account = new Account();
        meter = new Meter(account, EnergyType.GAS);
        localDate1 = LocalDate.of(2020, 12, 20);
        localDate2 = LocalDate.of(2021, 1, 21);
        localDate3 = LocalDate.of(2021, 2, 19);
        localDate4 = LocalDate.of(2021, 3, 21);
        localDate5 = LocalDate.of(2021, 4, 18);
        reading1 = new Reading(meter, 12345L, localDate1);
        reading2 = new Reading(meter, 12485L, localDate2);
        reading3 = new Reading(meter, 12741L, localDate3);
        reading4 = new Reading(meter, 12946L, localDate4);
        reading5 = new Reading(meter, 13015L, localDate5);
    }

    @Test
    public void canGetProperties(){
        assertEquals(0, meter.getReadings().size());

        meter.addReading(reading1);
        Long id = 123L;
        meter.setId(id);

        assertEquals(account, meter.getAccount());
        assertEquals(EnergyType.GAS, meter.getEnergyType());
        assertEquals(reading1, meter.getReadings().get(0));
        assertEquals(id, meter.getId());
    }

    @Test
    public void canCheckForDuplicateDateEntries(){
        assertEquals(false, meter.hasEntryForDate(localDate1));

        meter.addReading(reading1);
        assertEquals(true, meter.hasEntryForDate(localDate1));
        assertEquals(false, meter.hasEntryForDate(localDate2));
    }

    @Test
    public void canCalculateAverageDailyUsage(){
        List<Reading> readings = new ArrayList<>();
        meter.addReading(reading1);
        meter.addReading(reading2);
        assertEquals(4.375, meter.calculateAverageDailyUsage());

        meter.addReading(reading3);
        assertEquals(6.491803278688525d, meter.calculateAverageDailyUsage());
    }

    @Test
    public void returnsZeroIfInsufficientReadingsForAverage(){
        assertEquals(0d, meter.calculateAverageDailyUsage());

        meter.addReading(reading1);
        assertEquals(0d, meter.calculateAverageDailyUsage());
    }

}
