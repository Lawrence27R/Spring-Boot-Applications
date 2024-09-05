package com.aurionpro.dboperations.repository;

import java.util.List;

import com.aurionpro.dboperations.entity.Loan;

public interface LoanRepository {

    public List<Loan> getAllLoans();

    public void addLoan(Loan loan);

    public void updateLoan(Loan loan);
}
