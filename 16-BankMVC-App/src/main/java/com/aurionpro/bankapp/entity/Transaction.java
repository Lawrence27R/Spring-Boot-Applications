package com.aurionpro.bankapp.entity;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @ManyToOne
    @JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber", nullable = false)
    private CustomerAccount account;
    
    @Column(nullable = false)
    private long senderAccount;
    
    @Column(nullable = false)
    private long receiverAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOfTransaction typeOfTransaction;

    @Column(nullable = false)
    private double amount;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date date;

}
