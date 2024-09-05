package com.aurionpro.dboperations.service;

import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.dto.PageResponseDto;
import com.aurionpro.dboperations.entity.Salary;
import com.aurionpro.dboperations.entity.SalaryTransactions;

@Service
public interface SalaryService {

    Salary addSalary(Salary salary);
    
    PageResponseDto<Salary> getAllSalaries(int pageNumber, int pageSize);
    
    SalaryTransactions getSalaryTransactionById(int transactionId);
    
}
