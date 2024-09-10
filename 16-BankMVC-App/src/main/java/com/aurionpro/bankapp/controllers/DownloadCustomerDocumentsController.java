package com.aurionpro.bankapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.dto.DocumentDto;
import com.aurionpro.bankapp.service.AdminService;

@RestController
@RequestMapping("bankApp")
public class DownloadCustomerDocumentsController {

    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{customerId}")
    public ResponseEntity<List<DocumentDto>> getDocumentsByCustomerId(@PathVariable int customerId) {
        List<DocumentDto> documents = adminService.getDocumentsByCustomerId(customerId);
        
        if (documents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
}
