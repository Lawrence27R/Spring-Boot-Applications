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
import java.util.List;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private int clientId;

    @Column(name = "companyName")
    @NotBlank(message = "Company name is mandatory")
    private String companyName;

    @Column(name = "registrationNumber")
    @NotNull(message = "Registration number is mandatory")
    private int registrationNumber;

    @Column(name = "contactPerson")
    @NotBlank(message = "Contact person is mandatory")
    private String contactPerson;

    @Column(name = "contactEmail")
    @NotBlank(message = "Contact email is mandatory")
    @Email(message = "Contact email should be valid")
    private String contactEmail;

    @Column(name = "contactNumber")
    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be a 10-digit number")
    private String contactNumber;

    @Column(name = "address")
    @NotNull(message = "Address is mandatory")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Status is mandatory")
    private Status status;

    @Column(name = "creationDate")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Creation date is mandatory")
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "kycstatus")
    @NotNull(message = "KYC status is mandatory")
    private KycStatus kycstatus;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
