package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.dto.PageResponseDto;
import com.aurionpro.dboperations.entity.Employee;
import com.aurionpro.dboperations.entity.SalaryAccount;
import com.aurionpro.dboperations.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public PageResponseDto<Employee> getAllEmployees(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        PageResponseDto<Employee> employeeResponseDto = new PageResponseDto<>();
        employeeResponseDto.setTotalPages(employeePage.getTotalPages());
        employeeResponseDto.setTotalElements(employeePage.getTotalElements());
        employeeResponseDto.setSize(employeePage.getSize());
        employeeResponseDto.setContents(employeePage.getContent());
        employeeResponseDto.setLastPage(employeePage.isLast());

        return employeeResponseDto;
    }

    @Override
    public SalaryAccount getEmployeeSalaryAccount(int employeeId) {
        Optional<Employee> employeeData = employeeRepository.findById(employeeId);
        if (employeeData.isEmpty()) {
            return null;
        }
        return employeeData.get().getSalaryAccount();
    }

    @Override
    public SalaryAccount updateEmployeeSalaryAccount(int employeeId, SalaryAccount salaryAccount) {
        Optional<Employee> employeeData = employeeRepository.findById(employeeId);
        if (employeeData.isEmpty()) {
            return null;
        }

        Employee dbEmployee = employeeData.get();
        dbEmployee.setSalaryAccount(salaryAccount);
        employeeRepository.save(dbEmployee);

        return dbEmployee.getSalaryAccount();
    }
}
