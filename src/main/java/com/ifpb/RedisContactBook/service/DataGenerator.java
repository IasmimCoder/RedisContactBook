package com.ifpb.RedisContactBook.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.javafaker.Faker;
import com.ifpb.RedisContactBook.model.Contact;

@Service
public class DataGenerator {

     @Autowired
    private ContactService contactService;

    private final Faker faker = new Faker();

    public void generateAndSaveContacts(int numberOfContacts) {
        for (int i = 0; i < numberOfContacts; i++) {
            Contact contact = generateRandomContact();
            contactService.save(contact);
        }
    }

    public Contact generateRandomContact() {
        String id = UUID.randomUUID().toString(); // Gera um ID único
        String name = faker.name().fullName();
        String ddd = faker.number().digits(2); // DDD com 2 dígitos
        String number = faker.phoneNumber().phoneNumber().replaceAll("[^\\d]", ""); // Número de telefone, removendo caracteres não numéricos
        String secondNumber = faker.phoneNumber().phoneNumber().replaceAll("[^\\d]", ""); // Segundo número de telefone
        String email = faker.internet().emailAddress();

        return new Contact(id, name, ddd, number, secondNumber, email);
    }
}