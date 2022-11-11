package com.example.SPTest.models;

import com.example.SPTest.enums.EnergyType;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountReadings {

    private Long accountId;
    private List<ReadingResponse> gasReadings = new ArrayList<>();
    private List<ReadingResponse> elecReadings = new ArrayList<>();

    private Integer gasComparison;

    private Integer elecComparison;

    public AccountReadings(Account account) {
        this.accountId = account.getId();
        Map<EnergyType, List<Meter>> metersForAccount = account.getMeters().stream().collect(Collectors.groupingBy(Meter::getEnergyType));

        List<Meter> gasMeters = metersForAccount.get(EnergyType.GAS);
        List<Meter> elecMeters = metersForAccount.get(EnergyType.ELECTRICITY);

        this.gasReadings = gasMeters.stream().flatMap(meter -> meter.getReadings().stream()).map(reading -> new ReadingResponse(reading)).toList();

        if(elecMeters != null){
            this.elecReadings = elecMeters.stream().flatMap(meter -> meter.getReadings().stream()).map(reading -> new ReadingResponse(reading)).toList();
        }
    }

    public void findEnergyTypesForReadings(){
        Optional.ofNullable(this.gasReadings).orElseGet(Collections::emptyList).stream().forEach(reading -> reading.setEnergyType(EnergyType.GAS));
        Optional.ofNullable(this.elecReadings).orElseGet(Collections::emptyList).stream().forEach(reading -> reading.setEnergyType(EnergyType.ELECTRICITY));
    }

    public List<ReadingResponse> allReadingResponses(){
        return Stream.concat(Optional.ofNullable(this.gasReadings).orElseGet(Collections::emptyList).stream(), Optional.ofNullable(this.elecReadings).orElseGet(Collections::emptyList).stream()).toList();
    }

}
