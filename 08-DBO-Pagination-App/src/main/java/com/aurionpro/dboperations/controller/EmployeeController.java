package com.aurionpro.dboperations.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.entity.Employee;
import com.aurionpro.dboperations.service.EmployeeService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/employeeApp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    @Transactional
    public String addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return "Employee added successfully";
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable int employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/employees")
    @Transactional
    public String updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return "Employee updated successfully";
    }

    @GetMapping("/employees")
    public ResponseEntity<Page<Employee>> getEmployees(@RequestParam(required = false) String name,
                                                        @RequestParam int pageSize,
                                                        @RequestParam int pageNumber) {
        if (name != null) {
            return ResponseEntity.ok(employeeService.getEmployeeByName(name, pageSize, pageNumber));
        }
        return ResponseEntity.ok(employeeService.getAllEmployees(pageSize, pageNumber));
    }
}
