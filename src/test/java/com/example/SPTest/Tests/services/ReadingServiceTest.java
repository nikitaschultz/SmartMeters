package com.example.SPTest.Tests.services;

import com.example.SPTest.exceptions.AccountNotFoundException;
import com.example.SPTest.exceptions.MissingFieldException;
import com.example.SPTest.models.Account;
import com.example.SPTest.models.AccountReadings;
import com.example.SPTest.repositories.AccountRepository;
import com.example.SPTest.repositories.MeterRepository;
import com.example.SPTest.repositories.ReadingRepository;
import com.example.SPTest.services.ReadingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ReadingService.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(ReadingService.class)
public class ReadingServiceTest {

    @MockBean
    private ReadingRepository readingRepository;
    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private MeterRepository meterRepository;

    @InjectMocks
    private ReadingService readingService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test(expected = MissingFieldException.class)
    public void noAccountIdProvided() throws Exception {
        readingService.findByAccountId(null);
    }

    @Test(expected = MissingFieldException.class)
    public void accountNotFound() throws Exception {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));

        readingService.saveReadings(new AccountReadings());
    }

    // Many additional test scenarios required
    // - Returns readings with gas and elec comparison when data is valid
    // - Throws account not found exception
    // - Returns reading response when data is valid
    // - Throws exception when meter and reading energy type mismatch
    // - etc.

}
