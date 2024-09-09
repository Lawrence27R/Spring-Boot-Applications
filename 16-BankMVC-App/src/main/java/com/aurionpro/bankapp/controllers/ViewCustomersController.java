package com.aurionpro.bankapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.dto.AdminGetCustomerDto;
import com.aurionpro.bankapp.dto.CustomerDto;
import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.service.AdminService;

@RestController
@RequestMapping("/bankApp")
public class ViewCustomersController {

    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/viewCustomers")
    public ResponseEntity<PageResponseDto<AdminGetCustomerDto>> viewAllCustomers(
            @RequestBody(required = false) CustomerDto customerDto,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {

        PageResponseDto<AdminGetCustomerDto> response = adminService.getFilteredCustomers(
                customerDto,
                pageNumber,
                pageSize
        );

        return ResponseEntity.ok(response);
    }
}
