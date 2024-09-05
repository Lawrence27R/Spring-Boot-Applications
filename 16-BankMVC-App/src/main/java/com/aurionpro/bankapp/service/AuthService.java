package com.aurionpro.bankapp.service;

import com.aurionpro.bankapp.dto.LoginDto;
import com.aurionpro.bankapp.dto.RegistrationDto;
import com.aurionpro.bankapp.entity.User;

public interface AuthService {

	User register(RegistrationDto registrationDto);
	String login(LoginDto loginDto);
}
