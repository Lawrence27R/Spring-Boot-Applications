package com.aurionpro.bankapp.service;

import java.util.Date;

import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.dto.TransactionDto;

public interface TransactionService {

	PageResponseDto<TransactionDto> getFilteredTransactions(Long accountNumber, Date startDate, Date endDate,
			String typeOfTransaction, int pageNumber, int pageSize);

	PageResponseDto<TransactionDto> getAllTransactions(Long accountNumber, Date startDate, Date endDate, String typeOfTransaction,
			int pageNumber, int pageSize);
}
