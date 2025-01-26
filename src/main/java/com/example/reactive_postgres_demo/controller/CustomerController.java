package com.example.reactive_postgres_demo.controller;


import com.example.reactive_postgres_demo.dto.CustomerDto;
import com.example.reactive_postgres_demo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/customers")
@Tag(name = "Customer", description = "Customer management API")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(
            summary = "Retrieve all customers",
            description = "Retrieve all customers"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CustomerDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public Flux<CustomerDto> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    @Operation(
            summary = "Retrieve a customer by ID",
            description = "Retrieve a customer by ID"
    )
    @GetMapping("/{id}")
    public Mono<CustomerDto> getCustomerById(@PathVariable("id") Integer id){
        return customerService.getCustomer(id);
    }

    @Operation(
            summary = "Add new customer",
            description = "Add new customer"
    )
    @PostMapping
    public Mono<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto){
      return customerService.addCustomer(customerDto);
    }

    @Operation(
            summary = "Update customer",
            description = "Update customer"
    )
    @PutMapping
    public Mono<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto){
        return customerService.updateCustomer(customerDto);
    }

    @Operation(
            summary = "Delete customer by id",
            description = "Delete customer by id"
    )
    @DeleteMapping("/{id}")
    public Mono<Void> deleteCustomer(@PathVariable Integer id){
        return customerService.deleteCustomer(id);
    }
}
