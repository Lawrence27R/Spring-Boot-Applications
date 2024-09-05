package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.SalaryAccountDto;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.repository.SalaryAccountRepository;

@Service
public class SalaryAccountServiceImpl implements SalaryAccountService {

    @Autowired
    private SalaryAccountRepository salaryAccountRepo;

    @Override
    public SalaryAccountDto addSalary(SalaryAccountDto salaryAccountDto) {
        SalaryAccount salaryAccount = toSalaryAccountMapper(salaryAccountDto);
        salaryAccount = salaryAccountRepo.save(salaryAccount);
        return toSalaryAccountDtoMapper(salaryAccount);
    }

    @Override
    public PageResponseDto<SalaryAccountDto> getAllSalaryAccounts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<SalaryAccount> salaryAccountPage = salaryAccountRepo.findAll(pageable);

        List<SalaryAccountDto> salaryAccountDtoList = new ArrayList<>();
        for (SalaryAccount salaryAccount : salaryAccountPage.getContent()) {
            salaryAccountDtoList.add(toSalaryAccountDtoMapper(salaryAccount));
        }

        PageResponseDto<SalaryAccountDto> salaryAccountPageDto = new PageResponseDto<>();
        salaryAccountPageDto.setTotalPages(salaryAccountPage.getTotalPages());
        salaryAccountPageDto.setTotalElements(salaryAccountPage.getTotalElements());
        salaryAccountPageDto.setSize(salaryAccountPage.getSize());
        salaryAccountPageDto.setContents(salaryAccountDtoList);
        salaryAccountPageDto.setLastPage(salaryAccountPage.isLast());

        return salaryAccountPageDto;
    }

    private SalaryAccountDto toSalaryAccountDtoMapper(SalaryAccount salaryAccount) {
        SalaryAccountDto salaryAccountDto = new SalaryAccountDto();
        salaryAccountDto.setAccountNumber(salaryAccount.getAccountNumber());
        salaryAccountDto.setAccountHolderName(salaryAccount.getAccountHolderName());
        return salaryAccountDto;
    }

    private SalaryAccount toSalaryAccountMapper(SalaryAccountDto salaryAccountDto) {
        SalaryAccount salaryAccount = new SalaryAccount();
        salaryAccount.setAccountNumber(salaryAccountDto.getAccountNumber());
        salaryAccount.setAccountHolderName(salaryAccountDto.getAccountHolderName());
        return salaryAccount;
    }
}
