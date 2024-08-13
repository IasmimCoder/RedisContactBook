package com.ifpb.RedisContactBook.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data // Gera getters, setters, toString(), equals() e hashCode() automaticamente
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@RedisHash()
public class Contact {
    @Id
    private String id;
    private String name;
    private String ddd;
    private String number;
    private String secondNumber;
    private String email;
}
