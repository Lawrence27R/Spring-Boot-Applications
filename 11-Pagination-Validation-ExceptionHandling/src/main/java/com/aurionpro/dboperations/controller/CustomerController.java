package com.aurionpro.dboperations.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Customer;
import com.aurionpro.dboperations.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customerApp")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<ResponsePageDto<Customer>> getAllCustomers(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        ResponsePageDto<Customer> customers = customerService.getAllCustomers(pageNumber, pageSize);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable int customerId) {
        Optional<Customer> customer = customerService.findCustomerById(customerId);
        
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/customers")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer added successfully.");
    }

    @GetMapping("/customers-name")
    public ResponseEntity<Page<Customer>> getCustomersByFirstName(
            @RequestParam String firstName,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        
        Page<Customer> customers = customerService.findByFirstName(firstName, pageable);
        
        return ResponseEntity.ok(customers);
    }

}
