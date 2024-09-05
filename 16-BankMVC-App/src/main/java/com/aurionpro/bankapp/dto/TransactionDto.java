package com.aurionpro.bankapp.dto;

import com.aurionpro.bankapp.entity.TypeOfTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private int transactionId;
    private Long senderAccountNumber;
    private Long receiverAccountNumber;
    private TypeOfTransaction typeOfTransaction;
    private double amount;
    private Date date;
}
