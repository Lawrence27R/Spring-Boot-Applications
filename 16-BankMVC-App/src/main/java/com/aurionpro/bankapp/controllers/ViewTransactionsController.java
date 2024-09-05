package com.aurionpro.bankapp.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.dto.TransactionDto;
import com.aurionpro.bankapp.service.TransactionService;

@RestController
@RequestMapping("/bankApp")
public class ViewTransactionsController {
	
    @Autowired
    private TransactionService transactionService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllTransactions")
    public ResponseEntity<PageResponseDto<TransactionDto>> getAllTransactions(
            @RequestParam(required = false) Long accountNumber,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(required = false) String typeOfTrans,
            @RequestParam int pageNumber, @RequestParam int pageSize) {

    	PageResponseDto<TransactionDto> transactions = transactionService.getAllTransactions(accountNumber, startDate, endDate, typeOfTrans, pageNumber, pageSize);
        return ResponseEntity.ok(transactions);
    }


}
