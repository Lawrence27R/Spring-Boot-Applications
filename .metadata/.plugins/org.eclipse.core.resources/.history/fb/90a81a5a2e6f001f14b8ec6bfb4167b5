package com.aurionpro.bankapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.dto.RegistrationDto;
import com.aurionpro.bankapp.entity.User;
import com.aurionpro.bankapp.service.AuthService;

@RestController
@RequestMapping("/bankApp")
public class AddCustomerController {
    
    @Autowired
    private AuthService authService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addcustomer")
    public ResponseEntity<User> addCustomer(@RequestBody RegistrationDto registrationDto) {
        return ResponseEntity.ok(authService.register(registrationDto));
    }
    
    
}
