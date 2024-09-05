package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.aurionpro.dboperations.entity.Employee;

public interface EmployeeService {

    void addEmployee(Employee employee);

    Optional<Employee> getEmployeeById(int employeeId);

    void updateEmployee(Employee employee);

    Page<Employee> getAllEmployees(int pageSize, int pageNumber);

    Page<Employee> getEmployeeByName(String name, int pageSize, int pageNumber);
}
