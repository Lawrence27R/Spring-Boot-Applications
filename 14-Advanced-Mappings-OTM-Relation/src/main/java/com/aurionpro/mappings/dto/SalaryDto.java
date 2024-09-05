package com.aurionpro.mappings.dto;

import java.util.Date;

import com.aurionpro.mappings.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaryDto {

    private int salaryId;
    private int salaryMonth;
    private double grossSalary;
    private double deductions;
    private double netSalary;
    private Date paymentDate;
    private Status status;
}
