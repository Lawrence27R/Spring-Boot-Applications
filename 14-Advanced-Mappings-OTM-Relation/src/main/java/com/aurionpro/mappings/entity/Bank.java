package com.aurionpro.mappings.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "banks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankId")
    private int bankId;

    @Column(name = "bankName")
    @NotBlank(message = "Bank name is mandatory")
    private String bankName;

    @Column(name = "branch")
    @NotBlank(message = "Branch is mandatory")
    private String branch;

    @Column(name = "ifsccode")
    @NotBlank(message = "IFSC code is mandatory")
    private String ifsccode;

    @OneToMany(mappedBy = "bank")
    private List<SalaryAccount> salaryAccounts;
}
