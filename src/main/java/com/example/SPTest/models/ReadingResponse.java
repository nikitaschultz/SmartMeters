package com.example.SPTest.models;

import com.example.SPTest.enums.EnergyType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class ReadingResponse {

    private Long id;
    private Long meterId;
    private Long reading;
    private LocalDate date;
    private Long usageSinceLastRead;
    private Long periodSinceLastRead;
    private Double avgDailyUsage;

    @Getter(AccessLevel.NONE)
    private EnergyType energyType;

    public ReadingResponse(Reading reading) {
        this.id = reading.getId();
        this.meterId = reading.getMeter().getId();
        this.reading = reading.getReading();
        this.date = reading.getDate();
        Map<String, Long> statsSinceLastRead = reading.statsSinceLastRead();
        if(statsSinceLastRead != null){
            this.usageSinceLastRead = statsSinceLastRead.get("usage");
            this.periodSinceLastRead = statsSinceLastRead.get("period");
        }
        this.avgDailyUsage = reading.getMeter().calculateAverageDailyUsage();
    }

    public boolean hasEnergyType(EnergyType energyType){
        return energyType == this.energyType;
    }

}
