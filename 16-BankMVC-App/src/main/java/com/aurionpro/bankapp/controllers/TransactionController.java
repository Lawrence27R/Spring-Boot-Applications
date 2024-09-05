package com.aurionpro.bankapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.entity.CustomerAccount;
import com.aurionpro.bankapp.entity.User;
import com.aurionpro.bankapp.security.JwtTokenProvider;
import com.aurionpro.bankapp.service.CustomerService;

@RestController
@RequestMapping("/bankApp")
public class TransactionController {
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(
            @RequestParam long customerAccountNum,
            @RequestParam double amount,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String tokenUsername = jwtTokenProvider.getUsername(extractedToken);
        
        Optional<User> customer = customerService.findEmailId(tokenUsername);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean hasAccount = false;
        for (CustomerAccount account : customer.get().getAccounts()) {
            if (account.getAccountNumber() == customerAccountNum) {
                hasAccount = true;
                break;
            }
        }

        if (!hasAccount) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            customerService.deposit(customerAccountNum, amount);
            return new ResponseEntity<>("Deposit successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deposit failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(
            @RequestParam long customerAccountNum,
            @RequestParam double amount,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String tokenUsername = jwtTokenProvider.getUsername(extractedToken);
        
        Optional<User> customer = customerService.findEmailId(tokenUsername);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean hasAccount = false;
        for (CustomerAccount account : customer.get().getAccounts()) {
            if (account.getAccountNumber() == customerAccountNum) {
                hasAccount = true;
                break;
            }
        }

        if (!hasAccount) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            customerService.withdraw(customerAccountNum, amount);
            return new ResponseEntity<>("Withdrawal successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Withdrawal failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam long fromAccountNum,
            @RequestParam long toAccountNum,
            @RequestParam double amount,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String tokenUsername = jwtTokenProvider.getUsername(extractedToken);
        
        Optional<User> customer = customerService.findEmailId(tokenUsername);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean hasAccount = false;
        for (CustomerAccount account : customer.get().getAccounts()) {
            if (account.getAccountNumber() == fromAccountNum) {
                hasAccount = true;
                break;
            }
        }

        if (!hasAccount) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            customerService.transfer(fromAccountNum, toAccountNum, amount);
            return new ResponseEntity<>("Transfer successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Transfer failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
