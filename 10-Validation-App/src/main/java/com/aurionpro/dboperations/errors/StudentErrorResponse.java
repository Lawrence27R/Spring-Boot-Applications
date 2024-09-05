package com.aurionpro.dboperations.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class StudentErrorResponse {

    private int status;
    private String errorMessage;
    private Date timeStamp;
}
