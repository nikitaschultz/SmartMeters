package com.example.SPTest.controllers;

import com.example.SPTest.models.*;
import com.example.SPTest.services.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reads")
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @GetMapping(value="/{id}")
    public ResponseEntity<AccountReadings> getReads(
            @PathVariable Long id
    ){

        return new ResponseEntity(readingService.findByAccountId(id), HttpStatus.OK);
    }

    @PostMapping(value="")
    public ResponseEntity<AccountReadings> postAccountReads(
            @RequestBody AccountReadings accountReadings
    ){
        return new ResponseEntity(readingService.saveReadings(accountReadings), HttpStatus.CREATED);
    }


}
