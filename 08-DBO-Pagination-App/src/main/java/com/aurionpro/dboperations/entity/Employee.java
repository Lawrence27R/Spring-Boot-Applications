package com.aurionpro.dboperations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private int employeeId;
    
    @Column(name = "firstName")
    private String firstName;
    
    @Column(name = "lastName")
    private String lastName;
    
    @Column(name = "phoneNumber")
    private int phoneNumber;
    
    @Column(name = "email")
    private String email;
    
    @Enumerated(EnumType.STRING) 
    @Column(name = "position")
    private Position position;
    
    @Temporal(TemporalType.DATE) 
    @Column(name = "hireDate")
    private Date hireDate;
    
    @Column(name = "salary")
    private double salary;
    
    @Column(name = "bankAccountNumber")
    private int bankAccountNumber;
    
    @Column(name = "bankIfscNumber")
    private String bankIfscNumber;
    
    @Enumerated(EnumType.STRING) 
    @Column(name = "status")
    private EmployeeStatus status;
}
