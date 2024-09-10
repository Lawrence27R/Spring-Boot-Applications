package com.aurionpro.bankapp.service;

import com.aurionpro.bankapp.dto.CustomerRegistrationDto;
import com.aurionpro.bankapp.dto.LoginDto;
import com.aurionpro.bankapp.dto.RegistrationDto;
import com.aurionpro.bankapp.entity.User;

public interface AuthService {

	User adminRegister(RegistrationDto registrationDto);

	User customerRegister(CustomerRegistrationDto customerRegistrationDto);

	String login(LoginDto loginDto);
}
