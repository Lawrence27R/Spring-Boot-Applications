package com.aurionpro.dboperations.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "salary_transactions_app")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @NotNull(message = "Transaction date is mandatory")
    @PastOrPresent(message = "Transaction date cannot be in the future")
    @Temporal(TemporalType.DATE)
    @Column(name = "transaction_date")
    private Date transactionDate;

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be positive")
    @Column(name = "amount")
    private double amount;

    @NotNull(message = "Transaction status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

}
