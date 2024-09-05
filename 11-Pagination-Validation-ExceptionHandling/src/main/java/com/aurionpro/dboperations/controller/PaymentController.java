package com.aurionpro.dboperations.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.aurionpro.dboperations.entity.Payment;
import com.aurionpro.dboperations.entity.PaymentMode;
import com.aurionpro.dboperations.service.PaymentService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/paymentApp")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public ResponseEntity<ResponsePageDto<Payment>> getAllPayments(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        ResponsePageDto<Payment> payments = paymentService.getAllPayments(pageNumber, pageSize);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<Optional<Payment>> getPaymentById(@PathVariable int paymentId) {
        Optional<Payment> payment = paymentService.findPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/payments/mode")
    public ResponseEntity<Page<Payment>> getPaymentsByPaymentMode(
            @RequestParam PaymentMode paymentMode,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        Page<Payment> payments = paymentService.findByPaymentMode(paymentMode, PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(payments);
    }

    @PostMapping("/payments")
    @Transactional
    public ResponseEntity<String> addPayment(@Valid @RequestBody Payment payment) {
        paymentService.addPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Payment added successfully.");
    }
}
