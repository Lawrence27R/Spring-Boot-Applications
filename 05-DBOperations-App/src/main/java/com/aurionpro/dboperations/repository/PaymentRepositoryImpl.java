package com.aurionpro.dboperations.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dboperations.entity.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Payment> getAllPayments() {
        TypedQuery<Payment> query = manager.createQuery("select p from Payment p", Payment.class);
        return query.getResultList();
    }

    @Override
    public void addPayment(Payment payment) {
        manager.persist(payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        manager.merge(payment);
    }
}
