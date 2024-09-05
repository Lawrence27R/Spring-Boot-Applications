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
import com.aurionpro.dboperations.entity.Loan;
import com.aurionpro.dboperations.entity.LoanStatus;
import com.aurionpro.dboperations.service.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loanApp")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/loans")
    public ResponseEntity<ResponsePageDto<Loan>> getAllLoans(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        ResponsePageDto<Loan> loans = loanService.getAllLoans(pageNumber, pageSize);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/loans/{loanId}")
    public ResponseEntity<Optional<Loan>> getLoanById(@PathVariable int loanId) {
        Optional<Loan> loan = loanService.findLoanById(loanId);
        return ResponseEntity.ok(loan);
    }

    @PostMapping("/loans")
    public ResponseEntity<String> addLoan(@Valid @RequestBody Loan loan) {
        loanService.addLoan(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body("Loan added successfully.");
    }

    @GetMapping("/loans-status")
    public ResponseEntity<Page<Loan>> getLoanByStatus(
            @RequestParam LoanStatus loanStatus,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Loan> loans = loanService.findByLoanStatus(loanStatus, pageable);
        return ResponseEntity.ok(loans);
    }
}
