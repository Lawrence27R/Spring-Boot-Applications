package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Loan;
import com.aurionpro.dboperations.entity.LoanStatus;
import com.aurionpro.dboperations.exception.LoanDoesNotExistException;
import com.aurionpro.dboperations.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public void addLoan(Loan loan) {
        loanRepository.save(loan);
    }

    @Override
    public Page<Loan> findByLoanStatus(LoanStatus loanStatus, Pageable pageable) {
        Page<Loan> loans = loanRepository.findByLoanStatus(loanStatus, pageable);
        if (loans.isEmpty()) {
            throw new LoanDoesNotExistException("No loans found with the status: " + loanStatus);
        }
        return loans;
    }

    @Override
    public Optional<Loan> findLoanById(int loanId) {
        Optional<Loan> loan = loanRepository.findById(loanId);
        if (!loan.isPresent()) {
            throw new LoanDoesNotExistException("Loan with ID " + loanId + " does not exist.");
        }
        return loan;
    }

	@Override
	public ResponsePageDto<Loan> getAllLoans(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Loan> loanPage = loanRepository.findAll(pageable);

        ResponsePageDto<Loan> loanPageDto = new ResponsePageDto<>();
        loanPageDto.setTotalPages(loanPage.getTotalPages());
        loanPageDto.setTotalElements(loanPage.getTotalElements());
        loanPageDto.setSize(loanPage.getSize());
        loanPageDto.setContents(loanPage.getContent());
        loanPageDto.setLastPage(loanPage.isLast());

        return loanPageDto;
	}
}
