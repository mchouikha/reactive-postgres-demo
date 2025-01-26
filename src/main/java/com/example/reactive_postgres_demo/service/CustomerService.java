package com.example.reactive_postgres_demo.service;

import com.example.reactive_postgres_demo.dto.CustomerDto;
import com.example.reactive_postgres_demo.model.Customer;
import com.example.reactive_postgres_demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final ConverterCustomer converterCustomer;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ConverterCustomer converterCustomer) {
        this.customerRepository = customerRepository;
        this.converterCustomer =converterCustomer;
    }

    public Flux<CustomerDto> getAllCustomers(){
        Flux<Customer> customers =  customerRepository.findAll();
        return customers.map(converterCustomer::cutomerToCustomerDto);
    }

    public Mono<CustomerDto> getCustomer(Integer id){
        Mono<Customer> customer = customerRepository.findById(id);
        return customer.map(converterCustomer::cutomerToCustomerDto);
    }

    public Mono<CustomerDto> addCustomer(CustomerDto customerDto) {
        Mono<Customer> newCustomer = customerRepository.save(converterCustomer.cutomerDtoToCustomer(customerDto));
        return newCustomer.map(converterCustomer::cutomerToCustomerDto);
    }

    public Mono<CustomerDto> updateCustomer(CustomerDto customerDto) {
        Mono<Customer> customerUpdated = customerRepository.findById(customerDto.getId()).flatMap(customerDb -> {
            customerDb.setName(customerDto.getName());
            return customerRepository.save(customerDb);
        });
        return customerUpdated.map(converterCustomer::cutomerToCustomerDto);
    }

    public Mono<Void>  deleteCustomer(Integer id){
        return customerRepository.deleteById(id);
    }

}
