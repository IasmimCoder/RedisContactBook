package com.ifpb.RedisContactBook.model;

import java.util.UUID;

import com.github.javafaker.Faker;

public class DataGenerator {
    private Faker faker = new Faker();

    public Contact generateRandomContact() {
        String id = UUID.randomUUID().toString(); // Gera um ID único
        String name = faker.name().fullName();
        String ddd = faker.number().digits(2); // DDD com 2 dígitos
        String number = faker.phoneNumber().phoneNumber().replaceAll("[^\\d]", ""); // Número de telefone, removendo caracteres não numéricos
        String secondNumber = faker.phoneNumber().phoneNumber().replaceAll("[^\\d]", ""); // Segundo número de telefone
        String email = faker.internet().emailAddress();

        return new Contact(id, name, ddd, number, secondNumber, email);
    }

    // Métodos adicionais para gerar listas de contatos ou dados específicos
}