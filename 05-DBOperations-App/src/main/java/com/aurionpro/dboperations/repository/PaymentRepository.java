package com.aurionpro.dboperations.repository;

import java.util.List;

import com.aurionpro.dboperations.entity.Payment;

public interface PaymentRepository {

    public List<Payment> getAllPayments();

    public void addPayment(Payment payment);

    public void updatePayment(Payment payment);
}
