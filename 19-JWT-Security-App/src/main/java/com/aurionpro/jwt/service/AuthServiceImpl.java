package com.aurionpro.jwt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.jwt.dto.LoginDto;
import com.aurionpro.jwt.dto.RegistrationDto;
import com.aurionpro.jwt.entity.Role;
import com.aurionpro.jwt.entity.User;
import com.aurionpro.jwt.exception.UserApiException;
import com.aurionpro.jwt.repository.RoleRepository;
import com.aurionpro.jwt.repository.UserRepository;
import com.aurionpro.jwt.security.JwtTokenProvider;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private RoleRepository roleRepo;

	@Override
	public User register(RegistrationDto registrationDto) {
		if (userRepo.existsByUsername(registrationDto.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists");

		User user = new User();
		user.setUsername(registrationDto.getUsername());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

		List<Role> roles = new ArrayList<Role>();

		Role userRole = roleRepo.findByRolename(registrationDto.getRolename()).get();

		roles.add(userRole);
		user.setRole(roles);
		return userRepo.save(user);
	}

	@Override
	public String login(LoginDto loginDto) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication);

			return token;
		} catch (BadCredentialsException e) {
			throw new UserApiException(HttpStatus.NOT_FOUND, "Invaid login details");
		}
	}

}
