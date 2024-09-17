package com.aurionpro.bankapp.service;

import java.util.List;

import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.dto.TransactionDto;
import com.aurionpro.bankapp.dto.TransactionFilterDto;
import com.aurionpro.bankapp.entity.Transaction;

import io.jsonwebtoken.io.IOException;
import jakarta.mail.MessagingException;

public interface TransactionService {

    PageResponseDto<TransactionDto> getCustomerTransactions(TransactionFilterDto filterDto, int pageNumber, int pageSize);

    PageResponseDto<TransactionDto> getAllTransactions(TransactionFilterDto filterDto, int pageNumber, int pageSize);
    
    void sendMonthlyPassbook(String email, Long accountNumber) throws MessagingException, IOException;
    
    List<Transaction> getMonthlyTransactions(Long accountNumber);
}
