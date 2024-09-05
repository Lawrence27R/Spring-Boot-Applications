package com.aurionpro.bankapp.service;

import java.util.Optional;

import com.aurionpro.bankapp.dto.CustomerAccountInfoDto;
import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.entity.CustomerAccount;
import com.aurionpro.bankapp.entity.User;

public interface AdminService {
	Optional<User> findCustomerById(int customerId);

//	boolean addCustomer(RegistrationDto registrationDto);

	CustomerAccount createAccountForCustomer(User customer);

	PageResponseDto<CustomerAccountInfoDto> getAllCustomerAccounts(String firstname, String lastname,
			Long accountNumber, int pageNumber, int pageSize);

	public PageResponseDto<User> getAllCustomers(int pageNumber, int pageSize);

	public PageResponseDto<User> getFilteredCustomers(String firstname, String lastname, Integer customerId,
			int pageNumber, int pageSize);

}
