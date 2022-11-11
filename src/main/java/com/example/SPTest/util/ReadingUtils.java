package com.example.SPTest.util;

import com.example.SPTest.models.Meter;
import com.example.SPTest.models.Reading;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ReadingUtils {
    public static final List<Reading> sortReadings(List<Reading> readings){
        return Optional.ofNullable(readings).orElseGet(Collections::emptyList).stream().sorted(Comparator.comparing(reading -> reading.getDate())).toList();
    }

    public static final Integer getMeterComparisonPosition(List<Meter> meters, Long value){
        if(meters.size() == 0){ return null;}
        List<Long> orderedMeters = meters.stream()
                .sorted(Comparator.comparingDouble(Meter::calculateAverageDailyUsage))
                .filter(val -> !val.equals(0)).map(Meter::getId).toList();

        return orderedMeters.indexOf(value) + 1;
    }
}
