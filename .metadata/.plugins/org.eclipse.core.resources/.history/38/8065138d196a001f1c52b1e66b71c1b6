package com.aurionpro.bankapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.dto.CustomerAccountInfoDto;
import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.entity.CustomerAccount;
import com.aurionpro.bankapp.entity.User;
import com.aurionpro.bankapp.service.AdminService;

@RestController
@RequestMapping("/bankApp")
public class AddCustomerBankAccountController {

	@Autowired
	private AdminService adminService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findCustomer")
	public ResponseEntity<?> findCustomerById(@RequestParam int customerId) {
		Optional<User> customer = adminService.findCustomerById(customerId);
		if (!customer.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
		}
		return ResponseEntity.ok(customer.get());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createAccount")
	public ResponseEntity<?> createAccountForCustomer(@RequestParam int customerId) {
		Optional<User> customer = adminService.findCustomerById(customerId);
		if (!customer.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
		}
		CustomerAccount account = adminService.createAccountForCustomer(customer.get());
		return ResponseEntity.ok(account);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customers")
	public ResponseEntity<PageResponseDto<CustomerAccountInfoDto>> getAllCustomerAccounts(
			@RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname,
			@RequestParam(required = false) Long accountNumber, @RequestParam int pageNumber,
			@RequestParam int pageSize) {

		PageResponseDto<CustomerAccountInfoDto> response = adminService.getAllCustomerAccounts(firstname, lastname,
				accountNumber, pageNumber, pageSize);
		return ResponseEntity.ok(response);
	}

}
