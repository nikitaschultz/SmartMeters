package com.example.SPTest.models;
import com.example.SPTest.util.ReadingUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="readings")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reading_id")
    private Long id;

    @JsonIgnoreProperties({"readings"})
    @ManyToOne
    @JoinColumn(name="meter_id", nullable=false)
    private Meter meter;

    @Column(name="reading", nullable = false)
    private Long reading;

    @Column(name="date", nullable = false)
    private LocalDate date;

    public Reading(Meter meter, Long reading, LocalDate date) {
        this.meter = meter;
        this.reading = reading;
        this.date = date;
    }

    public Map<String, Long> statsSinceLastRead(){
        List<Reading> allMeterReads = ReadingUtils.sortReadings(this.getMeter().getReadings());
        int indexOfPreviousRead = allMeterReads.indexOf(this) - 1;
        if(indexOfPreviousRead >= 0){
            Reading previousRead = allMeterReads.get(indexOfPreviousRead);
            HashMap<String, Long> response = new HashMap<String, Long>();
            response.put("usage", this.reading - previousRead.getReading());
            response.put("period", previousRead.getDate().until(this.date, ChronoUnit.DAYS));
            return response;
        }
        return null;
    };

}
