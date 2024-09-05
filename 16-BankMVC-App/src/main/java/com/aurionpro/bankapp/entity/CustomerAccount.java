package com.aurionpro.bankapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customerAccount")
@Data
@NoArgsConstructor
public class CustomerAccount {

	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNumber;

	@NotNull(message = "Customer balance is required")
	@Positive(message = "Customer balance must be greater than 1000")
	@Column(nullable = false)
	private double customerBalance;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "userId", nullable = false)
	private User user;
}
