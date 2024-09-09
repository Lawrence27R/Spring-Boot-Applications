package com.aurionpro.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {

	private String username;
	private String password;
	private String captcha;
	private String captchaId;
}
