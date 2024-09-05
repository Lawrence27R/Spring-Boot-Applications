package com.aurionpro.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.dto.BankDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.service.BankService;

@RestController
@RequestMapping("/bankApp")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/bank")
    public ResponseEntity<BankDto> addBank(@RequestBody BankDto bankDto) {
        return ResponseEntity.ok(bankService.addBank(bankDto));
    }

    @GetMapping("/banks")
    public ResponseEntity<PageResponseDto<BankDto>> getAllBanks(
            @RequestParam int pageNumber, 
            @RequestParam int pageSize) {
        return ResponseEntity.ok(bankService.getAllBanks(pageNumber, pageSize));
    }

    @GetMapping("/banks/{bankId}")
    public ResponseEntity<BankDto> getBankById(@PathVariable int bankId) {
        BankDto bankDto = bankService.getBankById(bankId);
        return ResponseEntity.ok(bankDto);
    }
}
