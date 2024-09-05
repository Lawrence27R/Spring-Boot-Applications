package com.aurionpro.dboperations.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "salaryApp")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id")
    private int salaryId;

    @NotNull(message = "Salary month is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "salary_month")
    private SalaryMonth salaryMonth;

    @NotNull(message = "Gross salary is mandatory")
    @Positive(message = "Gross salary must be positive")
    @Column(name = "gross_salary")
    private double grossSalary;

    @NotNull(message = "Deductions are mandatory")
    @PositiveOrZero(message = "Deductions cannot be negative")
    @Column(name = "deductions")
    private double deductions;

    @NotNull(message = "Net salary is mandatory")
    @PositiveOrZero(message = "Net salary cannot be negative")
    @Column(name = "net_salary")
    private double netSalary;

    @NotNull(message = "Payment date is mandatory")
    @PastOrPresent(message = "Payment date cannot be in the future")
    @Temporal(TemporalType.DATE)
    @Column(name = "payment_date")
    private Date paymentDate;

    @NotNull(message = "Salary status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "salary_status")
    private SalaryStatus salaryStatus;

    @NotNull(message = "Transaction is mandatory")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    private SalaryTransactions transaction;
}
