package com.aurionpro.bankapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.bankapp.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	Page<Transaction> findByAccount_AccountNumber(Long accountNumber, Pageable pageable);
	
	List<Transaction> findByAccount_AccountNumberAndDateBetween(Long accountNumber, Date startDate, Date endDate);
}
