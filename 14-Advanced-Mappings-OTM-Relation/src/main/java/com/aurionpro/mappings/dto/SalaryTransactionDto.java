package com.aurionpro.mappings.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaryTransactionDto {

    private int transactionId;
    private Date transactionDate;
    private double amount;
}
