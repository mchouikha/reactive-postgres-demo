package com.example.reactive_postgres_demo.repository;

import com.example.reactive_postgres_demo.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
