package com.aurionpro.dboperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.entity.Payment;
import com.aurionpro.dboperations.service.PaymentService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/paymentApp")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> getAllPayments() {
		return ResponseEntity.ok(paymentService.getAllPayments());
	}
	
	@PostMapping("/payments")
	@Transactional
	public String addPayment(@RequestBody Payment payment) {
		paymentService.addPayment(payment);
		return "Payment added successfully.";
	}
	
	@PutMapping("/payments")
	@Transactional
	public String updatePayment(@RequestBody Payment payment) {
		paymentService.updatePayment(payment);
		return "Payment updated successfully.";
	}
}
