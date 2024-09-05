package com.aurionpro.dboperations.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
public class Loan {

	@Column(name = "loanId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	@Column
	private double loanAmount;
	@Column
	private double interestRate;
	@Column
	@Enumerated(EnumType.STRING)
	private LoanTerm loanTerm;
	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Column
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@Column
	@Enumerated(EnumType.STRING)
	private LoanStatus loanStatus;
	
	public Loan() {
		super();

	}
	
	public Loan(int loanId, double loanAmount, double interestRate, LoanTerm loanTerm, Date startDate, Date endDate,
			LoanStatus loanStatus) {
		super();
		this.loanId = loanId;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
		this.startDate = startDate;
		this.endDate = endDate;
		this.loanStatus = loanStatus;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public LoanTerm getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(LoanTerm loanTerm) {
		this.loanTerm = loanTerm;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanAmount=" + loanAmount + ", interestRate=" + interestRate
				+ ", loanTerm=" + loanTerm + ", startDate=" + startDate + ", endDate=" + endDate + ", loanStatus="
				+ loanStatus + "]";
	}
	
	
}
