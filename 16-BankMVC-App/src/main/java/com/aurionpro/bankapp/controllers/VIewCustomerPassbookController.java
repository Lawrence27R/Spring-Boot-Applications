package com.aurionpro.bankapp.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.dto.TransactionDto;
import com.aurionpro.bankapp.entity.CustomerAccount;
import com.aurionpro.bankapp.entity.User;
import com.aurionpro.bankapp.security.JwtTokenProvider;
import com.aurionpro.bankapp.service.CustomerService;
import com.aurionpro.bankapp.service.TransactionService;

@RestController
@RequestMapping("/bankApp")
public class VIewCustomerPassbookController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/viewPassbook")
	public ResponseEntity<PageResponseDto<TransactionDto>> viewPassbook(
			@RequestParam(required = true) Long customerAccountNum,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(required = false) String typeOfTrans, @RequestParam int pageNumber,
			@RequestParam int pageSize, @RequestHeader("Authorization") String token) {

		String extractedToken = token.replace("Bearer ", "");
		String tokenUsername = jwtTokenProvider.getUsername(extractedToken);

		Optional<User> customer = customerService.findEmailId(tokenUsername);
		if (!customer.isPresent()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		boolean hasAccount = false;
		for (CustomerAccount account : customer.get().getAccounts()) {
			if (account.getAccountNumber().equals(customerAccountNum)) {
				hasAccount = true;
				break;
			}

		}

		if (!hasAccount) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		PageResponseDto<TransactionDto> transactions = transactionService.getFilteredTransactions(customerAccountNum,
				startDate, endDate, typeOfTrans, pageNumber, pageSize);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
}
