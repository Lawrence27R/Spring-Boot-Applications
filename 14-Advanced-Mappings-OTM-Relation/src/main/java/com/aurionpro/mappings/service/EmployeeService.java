package com.aurionpro.mappings.service;

import com.aurionpro.mappings.dto.EmployeeDto;
import com.aurionpro.mappings.dto.PageResponseDto;

public interface EmployeeService {
    EmployeeDto addEmployee(EmployeeDto employeeDto);
    PageResponseDto<EmployeeDto> getAllEmployees(int pageNumber, int pageSize);
    EmployeeDto getEmployeeById(int employeeId);

}
