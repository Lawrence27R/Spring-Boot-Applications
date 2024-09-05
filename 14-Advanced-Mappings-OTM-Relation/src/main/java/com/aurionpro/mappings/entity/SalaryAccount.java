package com.aurionpro.mappings.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salary_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountNumber")
    private int accountNumber;

    @Column(name = "accountHolderName")
    @NotBlank(message = "Account holder name is mandatory")
    private String accountHolderName;

    @ManyToOne
    @JoinColumn(name = "bankId")
    private Bank bank;
}
