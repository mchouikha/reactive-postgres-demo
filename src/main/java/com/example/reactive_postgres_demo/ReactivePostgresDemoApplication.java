package com.example.reactive_postgres_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableR2dbcRepositories
public class ReactivePostgresDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivePostgresDemoApplication.class, args);
	}

}
