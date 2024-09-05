package com.aurionpro.dboperations.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employeesApp")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name should not exceed 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name should not exceed 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Phone number is mandatory")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @NotNull(message = "Hire date is mandatory")
    @PastOrPresent(message = "Hire date cannot be in the future")
    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hireDate;

    @NotNull(message = "Salary is mandatory")
    @Column(name = "salary")
    private double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Account number is mandatory")
    private SalaryAccount salaryAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EmployeeStatus status;
}
