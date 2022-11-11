package com.example.SPTest.components;

import com.example.SPTest.enums.EnergyType;
import com.example.SPTest.models.Account;
import com.example.SPTest.models.Meter;
import com.example.SPTest.models.Reading;
import com.example.SPTest.repositories.AccountRepository;
import com.example.SPTest.repositories.MeterRepository;
import com.example.SPTest.repositories.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    MeterRepository meterRepository;

    @Autowired
    ReadingRepository readingRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account1 = new Account();
        accountRepository.save(account1);
        Account account2 = new Account();
        accountRepository.save(account2);
        Account account3 = new Account();
        accountRepository.save(account3);
        Account account4 = new Account();
        accountRepository.save(account4);

        Meter meter1 = new Meter(account1, EnergyType.GAS);
        meterRepository.save(meter1);
        Meter meter2 = new Meter(account1, EnergyType.ELECTRICITY);
        meterRepository.save(meter2);
        Meter meter3 = new Meter(account2, EnergyType.GAS);
        meterRepository.save(meter3);
        Meter meter4 = new Meter(account2, EnergyType.ELECTRICITY);
        meterRepository.save(meter4);
        Meter meter5 = new Meter(account3, EnergyType.GAS);
        meterRepository.save(meter5);

        LocalDate localDate1 = LocalDate.of(2020, 12, 20);
        LocalDate localDate2 = LocalDate.of(2021, 1, 21);
        LocalDate localDate3 = LocalDate.of(2021, 2, 19);
        LocalDate localDate4 = LocalDate.of(2021, 3, 21);
        LocalDate localDate5 = LocalDate.of(2021, 4, 18);
        LocalDate localDate6 = LocalDate.of(2021, 5, 21);
        LocalDate localDate7 = LocalDate.of(2021, 6, 21);

        // account1, meter1 readings
        Reading readinga1m11 = new Reading(meter1, 12345L, localDate1);
        readingRepository.save(readinga1m11);
        Reading readinga1m12 = new Reading(meter1, 12485L, localDate2);
        readingRepository.save(readinga1m12);
        Reading readinga1m13 = new Reading(meter1, 12741L, localDate3);
        readingRepository.save(readinga1m13);
        Reading readinga1m14 = new Reading(meter1, 12946L, localDate4);
        readingRepository.save(readinga1m14);
        Reading readinga1m15 = new Reading(meter1, 13015L, localDate5);
        readingRepository.save(readinga1m15);

        // account1, meter2 readings
        Reading readinga1m21 = new Reading(meter2, 23345L, localDate1);
        readingRepository.save(readinga1m21);
        Reading readinga1m22 = new Reading(meter2, 25485L, localDate2);
        readingRepository.save(readinga1m22);
        Reading readinga1m23 = new Reading(meter2, 29741L, localDate3);
        readingRepository.save(readinga1m23);
        Reading readinga1m24 = new Reading(meter2, 31946L, localDate4);
        readingRepository.save(readinga1m24);
        Reading readinga1m25 = new Reading(meter2, 32017L, localDate5);
        readingRepository.save(readinga1m25);
        Reading readinga1m26 = new Reading(meter2, 32915L, localDate6);
        readingRepository.save(readinga1m26);
        Reading readinga1m27 = new Reading(meter2, 34015L, localDate7);
        readingRepository.save(readinga1m27);

        // account2, meter3 readings
        Reading readinga2m31 = new Reading(meter3, 42345L, localDate1);
        readingRepository.save(readinga2m31);
        Reading readinga2m32 = new Reading(meter3, 42785L, localDate2);
        readingRepository.save(readinga2m32);

        // account2, meter4 readings
        Reading readinga2m41 = new Reading(meter4, 9345L, localDate1);
        readingRepository.save(readinga2m41);

    }
}
