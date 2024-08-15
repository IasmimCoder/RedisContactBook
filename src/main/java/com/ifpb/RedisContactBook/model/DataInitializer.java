package com.ifpb.RedisContactBook.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ifpb.RedisContactBook.service.ContactService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ContactService contatoService;

    @Override
    public void run(String... args) throws Exception {
        DataGenerator dataGenerator = new DataGenerator();
        for (int i = 0; i < 10; i++) {
            Contact contato =  dataGenerator.generateRandomContact(); 
            contatoService.save(contato);
        }
    }
}
