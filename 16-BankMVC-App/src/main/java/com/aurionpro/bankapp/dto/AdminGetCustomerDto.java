package com.aurionpro.bankapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminGetCustomerDto {

    private int userId;
    private String firstname;
    private String lastname;
    private String emailId;
}
