package com.aurionpro.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.dto.SalaryAccountDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.service.SalaryAccountService;

@RestController
@RequestMapping("/salaryAccountApp")
public class SalaryAccountController {

    @Autowired
    private SalaryAccountService salaryAccountService;

    @PostMapping("/salaryAccount")
    public ResponseEntity<SalaryAccountDto> addSalaryAccount(@RequestBody SalaryAccountDto salaryAccountDto) {
        return ResponseEntity.ok(salaryAccountService.addSalary(salaryAccountDto));
    }

    @GetMapping("/salaryAccounts")
    public ResponseEntity<PageResponseDto<SalaryAccountDto>> getAllSalaryAccounts(
            @RequestParam int pageNumber, 
            @RequestParam int pageSize) {
        return ResponseEntity.ok(salaryAccountService.getAllSalaryAccounts(pageNumber, pageSize));
    }
}
