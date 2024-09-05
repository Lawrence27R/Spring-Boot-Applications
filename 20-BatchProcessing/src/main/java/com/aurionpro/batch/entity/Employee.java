package com.aurionpro.batch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "employees")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
	
	@Id
	@Column
	private int employeeId;
	@Column
	private String name;
	@Column
	private double salary;

}
