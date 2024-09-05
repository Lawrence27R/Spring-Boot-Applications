package com.aurionpro.mappings.dto;

import java.util.Date;

import com.aurionpro.mappings.entity.Position;
import com.aurionpro.mappings.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Position position;
    private Date hireDate;
    private double salary;
//    private int accountNumber;
    private Status status;
    private int clientId;
}
