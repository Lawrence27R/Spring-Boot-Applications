package com.aurionpro.mappings.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salaryId")
    private int salaryId;

    @Column(name = "salaryMonth")
    @NotNull(message = "Salary month is mandatory")
    private int salaryMonth;

    @Column(name = "grossSalary")
    @NotNull(message = "Gross salary is mandatory")
    @Positive(message = "Gross salary must be positive")
    private double grossSalary;

    @Column(name = "deductions")
    @NotNull(message = "Deductions are mandatory")
    @Positive(message = "Deductions must be positive")
    private double deductions;

    @Column(name = "netSalary")
    @NotNull(message = "Net salary is mandatory")
    @Positive(message = "Net salary must be positive")
    private double netSalary;

    @Column(name = "paymentDate")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Payment date is mandatory")
    private Date paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Status is mandatory")
    private Status status;

    @OneToMany(mappedBy = "salary", cascade = CascadeType.ALL)
    private List<SalaryTransaction> salaryTransactions;  
}
