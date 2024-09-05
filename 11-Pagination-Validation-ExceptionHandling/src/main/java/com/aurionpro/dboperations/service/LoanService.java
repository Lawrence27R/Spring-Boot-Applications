package com.aurionpro.dboperations.service;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Loan;
import com.aurionpro.dboperations.entity.LoanStatus;

public interface LoanService {

    ResponsePageDto<Loan> getAllLoans(int pageNumber, int pageSize);

    void addLoan(Loan loan);

    Page<Loan> findByLoanStatus(LoanStatus loanStatus, Pageable pageable);

    Optional<Loan> findLoanById(int loanId);
}
