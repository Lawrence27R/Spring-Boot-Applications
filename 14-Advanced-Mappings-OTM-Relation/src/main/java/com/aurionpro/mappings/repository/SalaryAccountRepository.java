package com.aurionpro.mappings.repository;

import com.aurionpro.mappings.entity.SalaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Integer> {
	
}
