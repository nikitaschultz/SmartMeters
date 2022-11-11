package com.example.SPTest.Tests.controllers;

import com.example.SPTest.controllers.ReadingController;
import com.example.SPTest.models.AccountReadings;
import com.example.SPTest.models.ReadingResponse;
import com.example.SPTest.services.ReadingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ReadingController.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(ReadingController.class)
public class ReadingControllerTest {

    @InjectMocks
    private ReadingController readingController;

    @MockBean
    private ReadingService readingService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    private AccountReadings accountReadings = new AccountReadings();
    private List<ReadingResponse> readingResponse = new ArrayList<>();

    @Test
    public void getReads() throws Exception {
        when(readingService.findByAccountId(1L)).thenReturn(accountReadings);

        ResponseEntity<AccountReadings> response = readingController.getReads(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), accountReadings);
    }

    @Test
    public void postAccountReads() throws Exception {
        when(readingService.saveReadings(accountReadings)).thenReturn(readingResponse);

        ResponseEntity<AccountReadings> response = readingController.postAccountReads(accountReadings);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody(), readingResponse);
    }


}
