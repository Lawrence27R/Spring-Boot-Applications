package com.aurionpro.mappings.service;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.SalaryAccountDto;

public interface SalaryAccountService {

    SalaryAccountDto addSalary(SalaryAccountDto salaryAccountDto);
    PageResponseDto<SalaryAccountDto> getAllSalaryAccounts(int pageNumber, int pageSize);
}
