package com.aurionpro.jwt.exception;

import org.springframework.http.HttpStatus;

public class UserApiException extends RuntimeException{
	
	public UserApiException(HttpStatus badRequest, String string) {
		// TODO Auto-generated constructor stub
	}
	private HttpStatus status;
	private String message;

}
