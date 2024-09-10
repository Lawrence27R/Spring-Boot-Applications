package com.aurionpro.bankapp.dto;

import com.aurionpro.bankapp.entity.KycStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRegistrationDto {

    private String firstname;
    private String lastname;
	private String emailId;
	private KycStatus kycStatus;
	private String password;
	private String rolename;
}
