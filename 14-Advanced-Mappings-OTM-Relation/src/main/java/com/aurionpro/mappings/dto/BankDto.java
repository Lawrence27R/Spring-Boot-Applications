package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankDto {

    private int bankId;
    private String bankName;
    private String branch;
    private String ifsccode;
}
