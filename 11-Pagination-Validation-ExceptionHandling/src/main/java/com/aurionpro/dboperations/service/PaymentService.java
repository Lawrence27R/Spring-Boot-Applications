package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Payment;
import com.aurionpro.dboperations.entity.PaymentMode;

public interface PaymentService {

    ResponsePageDto<Payment> getAllPayments(int pageNumber, int pageSize);

    void addPayment(Payment payment);
    
    Page<Payment> findByPaymentMode(PaymentMode paymentMode, Pageable pageable);

    Optional<Payment> findPaymentById(int paymentId);
}
