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
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "loans")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;

    @Column()
    @NotBlank(message = "Loan amount is mandatory")
    @Positive(message = "Loan amount must be positive")
    private double loanAmount;

    @Column()
    @NotBlank(message = "Interest rate is mandatory")
    @Positive(message = "Interest rate must be positive")
    private double interestRate;

    @Enumerated(EnumType.STRING)
    @Column()
    @NotBlank(message = "Loan term is mandatory")
    private LoanTerm loanTerm;

    @Column()
    @Temporal(TemporalType.DATE)
    @NotBlank(message = "Start date is mandatory")
    private Date startDate;

    @Column()
    @Temporal(TemporalType.DATE)
    @NotBlank(message = "End date is mandatory")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column()
    @NotBlank(message = "Loan status is mandatory")
    private LoanStatus loanStatus;
}
