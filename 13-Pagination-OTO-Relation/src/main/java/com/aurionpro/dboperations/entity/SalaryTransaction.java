package com.aurionpro.dboperations.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salary_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId")
    private int transactionId;

    @Column(name = "transactionDate")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Transaction date is mandatory")
    private Date transactionDate;

    @Column(name = "amount")
    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be positive")
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Status is mandatory")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "salaryId")
    @NotNull(message = "Salary ID is mandatory")
    private Salary salary;
}
