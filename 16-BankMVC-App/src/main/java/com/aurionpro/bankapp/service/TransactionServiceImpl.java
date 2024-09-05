package com.aurionpro.bankapp.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.dto.TransactionDto;
import com.aurionpro.bankapp.entity.Transaction;
import com.aurionpro.bankapp.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Override
    public PageResponseDto<TransactionDto> getFilteredTransactions(Long accountNumber, Date startDate, Date endDate, String typeOfTransaction, int pageNumber, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Transaction> transactions = transactionRepository.findByAccount_AccountNumber(accountNumber, pageable);
        return filterAndConvertToDto(transactions, startDate, endDate, typeOfTransaction);
    }

    @Override
    public PageResponseDto<TransactionDto> getAllTransactions(Long accountNumber, Date startDate, Date endDate, String typeOfTransaction, int pageNumber, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        return filterAndConvertToDto(transactions, startDate, endDate, typeOfTransaction);
    }

    private PageResponseDto<TransactionDto> filterAndConvertToDto(Page<Transaction> transactions, Date startDate, Date endDate, String typeOfTransaction) {
        List<TransactionDto> filteredTransactions = transactions.stream()
            .filter(transaction -> {
                boolean matches = true;

                if (startDate != null && transaction.getDate().before(startDate)) {
                    matches = false;
                }

                if (endDate != null && transaction.getDate().after(endDate)) {
                    matches = false;
                }

                if (typeOfTransaction != null && !transaction.getTypeOfTransaction().toString().equals(typeOfTransaction)) {
                    matches = false;
                }

                return matches;
            })
            .map(this::convertToDto)
            .collect(Collectors.toList());

        return new PageResponseDto<>(transactions.getTotalElements(), transactions.getTotalPages(), transactions.getSize(), filteredTransactions,
        		transactions.isLast());
    }

    private TransactionDto convertToDto(Transaction transaction) {
        return new TransactionDto(
            transaction.getTransactionId(),
            transaction.getSenderAccount(),
            transaction.getReceiverAccount(),
            transaction.getTypeOfTransaction(),
            transaction.getAmount(),
            transaction.getDate()
        );
    }
}
