package com.aurionpro.bankapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.bankapp.dto.PageResponseDto;
import com.aurionpro.bankapp.dto.TransactionDto;
import com.aurionpro.bankapp.dto.TransactionFilterDto;
import com.aurionpro.bankapp.entity.Transaction;
import com.aurionpro.bankapp.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public PageResponseDto<TransactionDto> getFilteredTransactions(TransactionFilterDto filterDto, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if (filterDto == null) {
            filterDto = new TransactionFilterDto();
        }

        Page<Transaction> transactions;
        if (filterDto.getCustomerAccountNum() != null) {
            transactions = transactionRepository.findByAccount_AccountNumber(filterDto.getCustomerAccountNum(), pageable);
        } else {
            transactions = transactionRepository.findAll(pageable);
        }
        return filterAndConvertToDto(transactions, filterDto);
    }

    @Override
    public PageResponseDto<TransactionDto> getAllTransactions(TransactionFilterDto filterDto, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if (filterDto == null) {
            filterDto = new TransactionFilterDto();
        }

        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        return filterAndConvertToDto(transactions, filterDto);
    }

    private PageResponseDto<TransactionDto> filterAndConvertToDto(Page<Transaction> transactions, TransactionFilterDto filterDto) {
        List<TransactionDto> filteredTransactions = transactions.stream()
            .filter(transaction -> {
                boolean matches = true;

                if (filterDto.getStartDate() != null && transaction.getDate().before(filterDto.getStartDate())) {
                    matches = false;
                }

                if (filterDto.getEndDate() != null && transaction.getDate().after(filterDto.getEndDate())) {
                    matches = false;
                }

                if (filterDto.getTypeOfTrans() != null && !transaction.getTypeOfTransaction().toString().equals(filterDto.getTypeOfTrans())) {
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
