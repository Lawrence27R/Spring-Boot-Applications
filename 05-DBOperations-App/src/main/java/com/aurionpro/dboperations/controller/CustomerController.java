package com.aurionpro.dboperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.entity.Customer;
import com.aurionpro.dboperations.service.CustomerService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/customerApp")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	@PostMapping("/customers")
	@Transactional
	public String addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return "Customer added successfully.";
	}
	
	@PutMapping("/customers")
	@Transactional
	public String updateCustomer(@RequestBody Customer customer) {
		customerService.updateCustomer(customer);
		return "Customer updated successfully.";
	}
}
