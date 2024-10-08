package com.aurionpro.bankapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.dto.EditCustomerProfileDto;
import com.aurionpro.bankapp.entity.User;
import com.aurionpro.bankapp.security.JwtTokenProvider;
import com.aurionpro.bankapp.service.AdminService;
import com.aurionpro.bankapp.service.CustomerService;

@RestController
@RequestMapping("/bankApp")
public class EditCustomerProfile {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;
	
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customer/{customerId}")
    public ResponseEntity<?> findCustomerById(@PathVariable int customerId) {
        Optional<User> customer = adminService.findCustomerById(customerId);
        if (!customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        return ResponseEntity.ok(customer.get());
    }

	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(
            @RequestParam int customerId,
            @RequestBody EditCustomerProfileDto request,
            @RequestHeader("Authorization") String token) {
        
        String extractedToken = token.replace("Bearer ", "");
        String tokenUsername = jwtTokenProvider.getUsername(extractedToken);
        
        Optional<User> customer = customerService.findEmailId(tokenUsername);
        if (!customer.isPresent() || customer.get().getUserId() != customerId) {
            return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
        }

        boolean isUpdated = customerService.updateCustomerProfile(customerId, request);

        if (isUpdated) {
            
            return new ResponseEntity<>("Profile updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Profile update failed", HttpStatus.BAD_REQUEST);
        }
    }

}
