package com.aurionpro.bankapp.dto;

import com.aurionpro.bankapp.entity.KycStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateKycRequestDto {

    private int customerId;
    private KycStatus kycStatus;
}
