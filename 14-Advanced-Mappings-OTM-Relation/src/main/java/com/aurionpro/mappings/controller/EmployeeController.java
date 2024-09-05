package com.aurionpro.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.dto.EmployeeDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.service.EmployeeService;

@RestController
@RequestMapping("/employeesApp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeDto));
    }

    @GetMapping("/employees")
    public ResponseEntity<PageResponseDto<EmployeeDto>> getAllEmployees(
            @RequestParam int pageNumber, 
            @RequestParam int pageSize) {
        return ResponseEntity.ok(employeeService.getAllEmployees(pageNumber, pageSize));
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }
}
