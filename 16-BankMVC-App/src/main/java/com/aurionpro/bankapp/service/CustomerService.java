package com.aurionpro.bankapp.service;

import java.util.Optional;

import com.aurionpro.bankapp.dto.EditCustomerProfileDto;
import com.aurionpro.bankapp.entity.User;

public interface CustomerService {
	
    void deposit(long customerAccountNum, double amount);

    void withdraw(long customerAccountNum, double amount);

    void transfer(long fromAccountNum, long toAccountNum, double amount);
	
	boolean updateCustomerProfile(int customerId, EditCustomerProfileDto request);

	Optional<User> findEmailId(String emailId);	

}
