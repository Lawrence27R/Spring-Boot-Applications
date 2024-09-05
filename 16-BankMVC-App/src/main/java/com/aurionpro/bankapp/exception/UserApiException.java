package com.aurionpro.bankapp.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UserApiException extends RuntimeException{
	
	public UserApiException(HttpStatus badRequest, String string) {
		// TODO Auto-generated constructor stub
	}
	private HttpStatus status;
	private String message;

}
