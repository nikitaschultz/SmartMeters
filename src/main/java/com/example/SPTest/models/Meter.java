package com.example.SPTest.models;
import com.example.SPTest.enums.EnergyType;
import com.example.SPTest.util.ReadingUtils;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meter_id")
    private Long id;

    @JsonBackReference(value="readings")
    @OneToMany(mappedBy = "meter", fetch = FetchType.LAZY)
    private List<Reading> readings = new ArrayList<>();

    @JsonIgnoreProperties({"meters"})
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name="energy_type")
    private EnergyType energyType;

    public Meter(Account account, EnergyType energyType) {
        this.account = account;
        this.energyType = energyType;
    }

    public Double calculateAverageDailyUsage(){
        if(this.readings == null || this.readings.size() < 2){
            return 0d;
        }
        List<Reading> sortedReadings = ReadingUtils.sortReadings(this.readings);
        Reading firstReading = sortedReadings.get(0);
        Reading lastReading = sortedReadings.get(readings.size()-1);
        Long sumOfAllReadings = lastReading.getReading() - firstReading.getReading();
        Long totalDays = firstReading.getDate().until(lastReading.getDate(), ChronoUnit.DAYS);
        return sumOfAllReadings.doubleValue() / totalDays;
    }

    public Boolean hasEntryForDate(LocalDate date){
        System.out.println(date + "  " + this.readings);
        return Optional.ofNullable(this.readings).orElseGet(Collections::emptyList).stream().anyMatch(reading -> reading.getDate().equals(date));
    }

    public void addReading(Reading reading){
        this.readings.add(reading);
    }

}
