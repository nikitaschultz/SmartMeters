package com.example.SPTest.Tests.models;

import com.example.SPTest.enums.EnergyType;
import com.example.SPTest.models.Account;
import com.example.SPTest.models.Meter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    private Account account;
    private Meter meter1;
    private Meter meter2;

    @BeforeEach
    public void before() {
        account = new Account();
        meter1 = new Meter(account, EnergyType.GAS);
        meter2 = new Meter(account, EnergyType.ELECTRICITY);
    }

    @Test
    public void canGetProperties(){
        assertEquals(0, account.getMeters().size());

        account.addMeter(meter1);
        account.addMeter(meter2);
        Long id = 123L;
        account.setId(id);

        assertEquals(meter1, account.getMeters().get(0));
        assertEquals(meter2, account.getMeters().get(1));
        assertEquals(id, account.getId());
    }
}
