package com.ifpb.RedisContactBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisContactBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisContactBookApplication.class, args);
	}

}
