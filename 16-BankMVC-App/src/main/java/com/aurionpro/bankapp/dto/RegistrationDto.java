package com.aurionpro.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationDto {

    private String firstname;
    private String lastname;
	private String emailId;
	private String password;
	private String rolename;
}
