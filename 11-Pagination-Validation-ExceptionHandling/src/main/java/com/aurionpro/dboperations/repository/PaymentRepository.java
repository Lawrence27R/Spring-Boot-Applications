package com.aurionpro.dboperations.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.dboperations.entity.Payment;
import com.aurionpro.dboperations.entity.PaymentMode;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Page<Payment> findByPaymentMode(PaymentMode paymentMode, Pageable pageable);
}
