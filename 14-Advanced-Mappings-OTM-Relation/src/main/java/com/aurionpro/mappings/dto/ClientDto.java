package com.aurionpro.mappings.dto;

import java.util.Date;

import com.aurionpro.mappings.entity.KycStatus;
import com.aurionpro.mappings.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDto {
	
    private int clientId;
    private String companyName;
    private int registrationNumber;
    private String contactPerson;
    private String contactEmail;
    private String contactNumber;
    private String address;
    private Status status;
    private Date creationDate;
    private KycStatus kycstatus;

}
