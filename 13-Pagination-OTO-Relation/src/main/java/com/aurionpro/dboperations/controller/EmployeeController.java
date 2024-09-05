package com.aurionpro.dboperations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.dto.PageResponseDto;
import com.aurionpro.dboperations.entity.Employee;
import com.aurionpro.dboperations.entity.SalaryAccount;
import com.aurionpro.dboperations.service.EmployeeService;

@RestController
@RequestMapping("/employeeApp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    @GetMapping("/employees")
    public ResponseEntity<PageResponseDto<Employee>> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        
        return ResponseEntity.ok(employeeService.getAllEmployees(pageNumber, pageSize));
    }

    @GetMapping("/employees/salaryAccount")
    public ResponseEntity<SalaryAccount> getEmployeeSalaryAccount(@RequestParam int employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeSalaryAccount(employeeId));
    }

    @PutMapping("/employees/salaryAccount")
    public ResponseEntity<SalaryAccount> updateEmployeeSalaryAccount(
            @RequestParam int employeeId,
            @RequestBody SalaryAccount salaryAccount) {
        return ResponseEntity.ok(employeeService.updateEmployeeSalaryAccount(employeeId, salaryAccount));
    }
}
