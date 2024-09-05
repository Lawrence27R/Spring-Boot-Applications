package com.aurionpro.dboperations.service;

import java.util.List;

import com.aurionpro.dboperations.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();

	public void addEmployee(Employee employee);

	public void updateEmployee(Employee employee);
}
