package com.aurionpro.mappings.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private int employeeId;

    @Column(name = "firstName")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Column(name = "lastName")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Column(name = "phoneNumber")
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a 10-digit number")
    private String phoneNumber;

    @Column(name = "email")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    @NotNull(message = "Position is mandatory")
    private Position position;

    @Column(name = "hireDate")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Hire date is mandatory")
    private Date hireDate;

    @Column(name = "salary")
    @NotNull(message = "Salary is mandatory")
    private double salary;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Status is mandatory")
    private Status status;
    
    @OneToOne
    @JoinColumn(name = "accountNumber")
    @NotNull(message = "Account number is mandatory")
    private int accountNumber;

    @ManyToOne
    @JoinColumn(name = "clientId")
    @NotNull(message = "Client is mandatory")
    private Client client;
}
