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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @Temporal(TemporalType.DATE)
    @NotBlank(message = "PaymentDate is mandatory")
    private Date paymentDate;

    @Column()
    @NotBlank(message = "Amount is mandatory")
    @Positive(message = "Amount should be positive")
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column()
    @NotBlank(message = "Payment mode is mandatory")
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    @Column()
    @NotBlank(message = "Payment status is mandatory")
    private PaymentStatus paymentStatus;
}
