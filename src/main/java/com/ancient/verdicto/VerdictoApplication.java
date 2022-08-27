package com.ancient.verdicto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class VerdictoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerdictoApplication.class, args);
	}

}
