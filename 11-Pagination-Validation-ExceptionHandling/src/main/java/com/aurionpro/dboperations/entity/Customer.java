package com.aurionpro.dboperations.entity;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    
    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Column(name = "first_name")
    private String firstName;	
    
    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Column(name = "last_name")
    private String lastName;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "email_id")
    private String emailId;
    
    @NotBlank(message = "Mobile number is mandatory")
    @Column(name = "mobile_number")
    private String mobileNumber;
}
