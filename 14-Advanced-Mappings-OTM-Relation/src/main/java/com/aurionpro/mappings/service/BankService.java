package com.aurionpro.mappings.service;

import com.aurionpro.mappings.dto.BankDto;
import com.aurionpro.mappings.dto.PageResponseDto;

public interface BankService {
    BankDto addBank(BankDto bankDto);
    PageResponseDto<BankDto> getAllBanks(int pageNumber, int pageSize);
    BankDto getBankById(int bankId);

}
