package com.aurionpro.bankapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.bankapp.entity.CustomerAccount;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Integer> {
	boolean existsByAccountNumber(long accountNumber);

	Optional<CustomerAccount> findByAccountNumber(long accountNumber);

	Page<CustomerAccount> findAll(Pageable pageable);

	Page<CustomerAccount> findByUserFirstnameIgnoreCase(String firstname, Pageable pageable);

	Page<CustomerAccount> findByUserLastnameIgnoreCase(String lastname, Pageable pageable);

	Page<CustomerAccount> findByUserFirstnameIgnoreCaseAndUser_LastnameIgnoreCase(String firstname, String lastname,
			Pageable pageable);

	Page<CustomerAccount> findByAccountNumber(long accountNumber, Pageable pageable);
}
