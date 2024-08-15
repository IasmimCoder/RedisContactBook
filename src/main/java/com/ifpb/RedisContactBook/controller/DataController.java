package com.ifpb.RedisContactBook.controller;

import com.ifpb.RedisContactBook.service.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataGenerator dataGenerator;

    @GetMapping("/generate")
    public String generateData(@RequestParam(defaultValue = "10") int numberOfContacts) {
        dataGenerator.generateAndSaveContacts(numberOfContacts);
        return "Generated " + numberOfContacts + " contacts.";
    }
}
