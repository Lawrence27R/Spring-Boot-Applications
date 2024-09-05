package com.aurionpro.dboperations.service;

import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.dto.PageResponseDto;
import com.aurionpro.dboperations.entity.Employee;
import com.aurionpro.dboperations.entity.SalaryAccount;

@Service
public interface EmployeeService {

    Employee addEmployee(Employee employee);
    
    PageResponseDto<Employee> getAllEmployees(int pageNumber, int pageSize);
    
    SalaryAccount getEmployeeSalaryAccount(int employeeId);
    
    SalaryAccount updateEmployeeSalaryAccount(int employeeId, SalaryAccount salaryAccount);
}
