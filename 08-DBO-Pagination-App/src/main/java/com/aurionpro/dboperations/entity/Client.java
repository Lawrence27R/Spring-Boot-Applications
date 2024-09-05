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
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor 
@Getter
@Setter
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientID")
    private int clientId;
    
    @Column(name = "companyName")
    private String companyName;
    
    @Column(name = "registrationNumber")
    private int registrationNumber;
    
    @Column(name = "contactPerson")
    private String contactPerson;
    
    @Column(name = "contactEmail")
    private String contactEmail;
    
    @Column(name = "contactNumber")
    private int contactNumber;
    
    @Column(name = "address")
    private String address;
    
    @Enumerated(EnumType.STRING) 
    @Column(name = "status")
    private Status status;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "creationDate")
    private Date creationDate;
    
    @Enumerated(EnumType.STRING) 
    @Column(name = "kycStatus")
    private KycStatus kycStatus;
}
