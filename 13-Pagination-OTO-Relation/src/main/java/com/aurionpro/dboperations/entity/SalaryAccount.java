package com.aurionpro.dboperations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salary_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private int accountNumber;

    @NotBlank(message = "Account holder name is mandatory")
    @Size(max = 100, message = "Account holder name should not exceed 100 characters")
    @Column(name = "account_holder_name", nullable = false)
    private String accountHolderName;
}
