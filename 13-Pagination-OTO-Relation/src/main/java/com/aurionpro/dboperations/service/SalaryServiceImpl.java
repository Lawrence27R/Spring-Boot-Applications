package com.aurionpro.dboperations.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.dto.PageResponseDto;
import com.aurionpro.dboperations.entity.Salary;
import com.aurionpro.dboperations.entity.SalaryTransactions;
import com.aurionpro.dboperations.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public Salary addSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public PageResponseDto<Salary> getAllSalaries(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Salary> salaryPage = salaryRepository.findAll(pageable);

        PageResponseDto<Salary> salaryResponseDto = new PageResponseDto<>();
        salaryResponseDto.setTotalPages(salaryPage.getTotalPages());
        salaryResponseDto.setTotalElements(salaryPage.getTotalElements());
        salaryResponseDto.setSize(salaryPage.getSize());
        salaryResponseDto.setContents(salaryPage.getContent());
        salaryResponseDto.setLastPage(salaryPage.isLast());

        return salaryResponseDto;
    }

    @Override
    public SalaryTransactions getSalaryTransactionById(int transactionId) {
        for (Salary salary : salaryRepository.findAll()) {
            if (salary.getTransaction() != null && salary.getTransaction().getTransactionId() == transactionId) {
                return salary.getTransaction();
            }
        }
        return null;
    }


}
