package com.aurionpro.mappings.entity;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private double amount;

    @ManyToOne
    @JoinColumn(name = "salaryId")
    private Salary salary;
}
