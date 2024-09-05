package com.aurionpro.dboperations.service;

import java.util.List;

import com.aurionpro.dboperations.entity.Payment;

public interface PaymentService {

    public List<Payment> getAllPayments();

    public void addPayment(Payment payment);

    public void updatePayment(Payment payment);
}
