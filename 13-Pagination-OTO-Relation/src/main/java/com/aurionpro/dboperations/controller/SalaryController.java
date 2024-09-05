package com.aurionpro.dboperations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.dto.PageResponseDto;
import com.aurionpro.dboperations.entity.Salary;
import com.aurionpro.dboperations.entity.SalaryTransactions;
import com.aurionpro.dboperations.service.SalaryService;

@RestController
@RequestMapping("SalariesApp")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping("/salary")
    public ResponseEntity<Salary> addSalary(@RequestBody Salary salary) {
        return ResponseEntity.ok(salaryService.addSalary(salary));
    }
    
    @GetMapping("/salary")
    public ResponseEntity<PageResponseDto<Salary>> getAllSalaries(
            @RequestParam int pageNumber, 
            @RequestParam int pageSize) {
        return ResponseEntity.ok(salaryService.getAllSalaries(pageNumber, pageSize));
    }

    @GetMapping("/salary/transaction")
    public ResponseEntity<SalaryTransactions> getSalaryTransactionById(
            @RequestParam int transactionId) {
        return ResponseEntity.ok(salaryService.getSalaryTransactionById(transactionId));
    }


}
