package com.aurionpro.bankapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.dto.TransactionDto;
import com.aurionpro.bankapp.dto.TransactionFilterDto;
import com.aurionpro.bankapp.service.TransactionService;

@RestController
@RequestMapping("/bankApp")
public class ViewTransactionsController {

    @Autowired
    private TransactionService transactionService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllTransactions")
    public ResponseEntity<PageResponseDto<TransactionDto>> getAllTransactions(
            @RequestBody(required = false) TransactionFilterDto filterDto,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {

        if (filterDto == null) {
            filterDto = new TransactionFilterDto();
        }

        PageResponseDto<TransactionDto> transactions = transactionService.getAllTransactions(
                filterDto,
                pageNumber,
                pageSize
        );

        return ResponseEntity.ok(transactions);
    }
}
