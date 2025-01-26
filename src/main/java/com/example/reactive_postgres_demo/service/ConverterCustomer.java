package com.example.reactive_postgres_demo.service;

import com.example.reactive_postgres_demo.dto.CustomerDto;
import com.example.reactive_postgres_demo.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConverterCustomer {

    public CustomerDto cutomerToCustomerDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }

    public Customer cutomerDtoToCustomer(CustomerDto customerDto){
        return Customer.builder()
                .name(customerDto.getName())
                .build();
    }

}
