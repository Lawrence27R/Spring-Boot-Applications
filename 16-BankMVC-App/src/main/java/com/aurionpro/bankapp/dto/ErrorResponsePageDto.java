package com.aurionpro.bankapp.dto;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class ErrorResponsePageDto {

    private int status;
    private Map<String, String> errorMessages;
    private Date timeStamp;
}