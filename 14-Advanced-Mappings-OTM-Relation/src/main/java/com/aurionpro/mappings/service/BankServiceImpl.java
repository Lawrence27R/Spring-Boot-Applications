package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.BankDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.entity.Bank;
import com.aurionpro.mappings.repository.BankRepository;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepo;
    
    @Override
    public BankDto addBank(BankDto bankDto) {
        Bank bank = toBankMapper(bankDto);
        bank = bankRepo.save(bank);
        return toBankDtoMapper(bank);
    }

    @Override
    public PageResponseDto<BankDto> getAllBanks(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Bank> bankPage = bankRepo.findAll(pageable);

        List<BankDto> bankDtoList = new ArrayList<>();
        for (Bank bank : bankPage.getContent()) {
            bankDtoList.add(toBankDtoMapper(bank));
        }

        PageResponseDto<BankDto> bankPageDto = new PageResponseDto<>();
        bankPageDto.setTotalPages(bankPage.getTotalPages());
        bankPageDto.setTotalElements(bankPage.getTotalElements());
        bankPageDto.setSize(bankPage.getSize());
        bankPageDto.setContents(bankDtoList);
        bankPageDto.setLastPage(bankPage.isLast());

        return bankPageDto;
    }

    @Override
    public BankDto getBankById(int bankId) {
        Optional<Bank> bankData = bankRepo.findById(bankId);
        if (bankData.isEmpty()) {
            return null;
        }
        Bank dbBank = bankData.get();
        return toBankDtoMapper(dbBank);
    }

    private BankDto toBankDtoMapper(Bank bank) {
        BankDto bankDto = new BankDto();
        bankDto.setBankId(bank.getBankId());
        bankDto.setBankName(bank.getBankName());
        bankDto.setBranch(bank.getBranch());
        bankDto.setIfsccode(bank.getIfsccode());
        return bankDto;
    }

    private Bank toBankMapper(BankDto bankDto) {
        Bank bank = new Bank();
        bank.setBankName(bankDto.getBankName());
        bank.setBranch(bankDto.getBranch());
        bank.setIfsccode(bankDto.getIfsccode());
        return bank;
    }
}
