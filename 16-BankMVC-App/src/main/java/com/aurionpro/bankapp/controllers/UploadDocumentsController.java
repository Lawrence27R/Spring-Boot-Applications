package com.aurionpro.bankapp.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.bankapp.service.CustomerService;

@RestController
@RequestMapping("bankApp")
public class UploadDocumentsController {

    @Autowired
    private CustomerService customerService;

	@PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocuments(@RequestParam("documents") List<MultipartFile> documents) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        boolean isUploaded = customerService.uploadDocuments(email, documents);

        if (isUploaded) {
            return new ResponseEntity<>("Documents uploaded successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to upload documents.", HttpStatus.BAD_REQUEST);
        }
    }
}
