package com.aurionpro.bankapp.service;

import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.dto.TransactionDto;
import com.aurionpro.bankapp.dto.TransactionFilterDto;

public interface TransactionService {

    PageResponseDto<TransactionDto> getCustomerTransactions(TransactionFilterDto filterDto, int pageNumber, int pageSize);

    PageResponseDto<TransactionDto> getAllTransactions(TransactionFilterDto filterDto, int pageNumber, int pageSize);
}
