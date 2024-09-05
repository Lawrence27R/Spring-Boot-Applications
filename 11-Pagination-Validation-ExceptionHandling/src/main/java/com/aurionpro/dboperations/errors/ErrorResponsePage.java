package com.aurionpro.dboperations.errors;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class ErrorResponsePage {

    private int status;
    private Map<String, String> errorMessages;
    private Date timeStamp;
}