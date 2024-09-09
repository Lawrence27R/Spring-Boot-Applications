package com.aurionpro.bankapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.entity.User;
import com.aurionpro.bankapp.service.AdminService;

@RestController
@RequestMapping("/bankApp")
public class ViewCustomersController {

	@Autowired
	private AdminService adminService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/viewCustomers")
	public ResponseEntity<PageResponseDto<User>> viewAllCustomers(@RequestParam(required = false) String firstname,
			@RequestParam(required = false) String lastname, @RequestParam(required = false) Integer customerId,
			@RequestParam int pageNumber, @RequestParam int pageSize) {

		PageResponseDto<User> response = adminService.getFilteredCustomers(firstname, lastname, customerId, pageNumber,
				pageSize);
		return ResponseEntity.ok(response);
	}
}
