package com.example.SPTest.services;

import com.example.SPTest.enums.EnergyType;
import com.example.SPTest.exceptions.*;
import com.example.SPTest.models.*;
import com.example.SPTest.repositories.AccountRepository;
import com.example.SPTest.repositories.MeterRepository;
import com.example.SPTest.repositories.ReadingRepository;
import com.example.SPTest.util.ReadingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReadingService {

    @Autowired
    ReadingRepository readingRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MeterRepository meterRepository;

    public AccountReadings findByAccountId(Long id) {
        //Validate Account
        if (id == null) {
            throw new MissingFieldException("accountId");
        }
        Account foundAccount = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        AccountReadings accountReadings = new AccountReadings(foundAccount);

        //Set Energy Usage Comparisons
        Map<EnergyType, List<Meter>> groupedMeters = meterRepository.findAll().stream().collect(Collectors.groupingBy(Meter::getEnergyType));

        accountReadings.setGasComparison(ReadingUtils.getMeterComparisonPosition(groupedMeters.get(EnergyType.GAS), accountReadings.getGasReadings().get(0).getMeterId()));
        accountReadings.setElecComparison(ReadingUtils.getMeterComparisonPosition(groupedMeters.get(EnergyType.ELECTRICITY), accountReadings.getElecReadings().get(0).getMeterId()));

        return accountReadings;
    }

    public List<ReadingResponse> saveReadings(AccountReadings accountReadings) {
        // Validate Account
        if (accountReadings.getAccountId() == null) {
            throw new MissingFieldException("accountId");
        }
        Account foundAccount = accountRepository.findById(accountReadings.getAccountId()).orElseThrow(() -> new AccountNotFoundException(accountReadings.getAccountId()));

        accountReadings.findEnergyTypesForReadings();
        List<ReadingResponse> allReadingResponses = accountReadings.allReadingResponses();

        // Validate Reading is Present
        if (allReadingResponses == null || allReadingResponses.size() == 0) {
            throw new MissingFieldException("gasReadings OR elecReadings");
        }

        ArrayList<Reading> readingsToSave = new ArrayList<>();

        for (ReadingResponse readingResponse : allReadingResponses) {
            // Validate Meter
            if (readingResponse.getMeterId() == null) {
                throw new MissingFieldException("{'[EnergyType]readings': meterId}");
            }
            Meter foundMeter = meterRepository.findById(readingResponse.getMeterId()).orElseThrow(() -> new MeterNotFoundException(readingResponse.getMeterId()));
            if (accountReadings.getAccountId() != foundMeter.getAccount().getId()) {
                throw new MeterAccountMismatchException(foundMeter.getId(), accountReadings.getAccountId());
            }

            // Validate Reading Details
            if (readingResponse.getDate() == null) {
                throw new MissingFieldException("{'[EnergyType]readings': date}");
            }
            if (readingResponse.getDate().getClass() != LocalDate.class) {
                throw new TypeMismatchException("Date", readingResponse.getDate().getClass().toString());
            }
            if (readingResponse.getReading() == null) {
                throw new MissingFieldException("{'[EnergyType]readings': reading}");
            }
            if (!readingResponse.hasEnergyType(foundMeter.getEnergyType())) {
                throw new MeterReadingEnergyTypeMismatchException(foundMeter.getEnergyType(), foundMeter.getId());
            }
            if (foundMeter.hasEntryForDate(readingResponse.getDate())) {
                throw new ReadingDateDuplicateException(readingResponse.getDate());
            }

            readingsToSave.add(new Reading(foundMeter, readingResponse.getReading(), readingResponse.getDate()));
        }

        readingRepository.saveAll(readingsToSave);
        return allReadingResponses;
    }
}
