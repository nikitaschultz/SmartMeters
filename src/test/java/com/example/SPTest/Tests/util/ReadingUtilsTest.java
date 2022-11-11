package com.example.SPTest.Tests.util;

import com.example.SPTest.enums.EnergyType;
import com.example.SPTest.models.Account;
import com.example.SPTest.models.Meter;
import com.example.SPTest.models.Reading;
import com.example.SPTest.util.ReadingUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadingUtilsTest {

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
    public void before() {
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
    public void canSortReadings(){
        List<Reading> readings = new ArrayList<>();
        readings.add(reading3);
        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading5);
        readings.add(reading4);

        List<Reading> sortedReadings = ReadingUtils.sortReadings(readings);
        assertEquals(reading1, sortedReadings.get(0));
        assertEquals(reading3, sortedReadings.get(2));
        assertEquals(reading5, sortedReadings.get(4));
    }

    @Test
    public void returnsEmptyArrayIfNull(){
        List<Reading> readings = new ArrayList<>();

        List<Reading> sortedReadings = ReadingUtils.sortReadings(readings);
        assertEquals(0, sortedReadings.size());
    }

    //Tests for getMeterComparisonPosition required
}