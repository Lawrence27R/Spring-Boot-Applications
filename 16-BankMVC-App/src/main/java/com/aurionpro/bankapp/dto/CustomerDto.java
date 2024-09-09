package com.aurionpro.bankapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {

    private String firstname;
    private String lastname;
    private int customerId;
}
