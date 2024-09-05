package com.aurionpro.dboperations.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dboperations.entity.Loan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Loan> getAllLoans() {
        TypedQuery<Loan> query = manager.createQuery("select l from Loan l", Loan.class);
        return query.getResultList();
    }

    @Override
    public void addLoan(Loan loan) {
        manager.persist(loan);
    }

    @Override
    public void updateLoan(Loan loan) {
        manager.merge(loan);
    }
}