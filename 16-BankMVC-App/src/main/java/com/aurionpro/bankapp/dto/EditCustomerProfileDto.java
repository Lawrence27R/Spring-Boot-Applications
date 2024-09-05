package com.aurionpro.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCustomerProfileDto {

    private String firstname;
    private String lastname;
    private String emailId;
    private String customerPassword;
}
