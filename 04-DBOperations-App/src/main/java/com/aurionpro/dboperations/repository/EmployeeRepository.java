package com.aurionpro.dboperations.repository;

import java.util.List;

import com.aurionpro.dboperations.entity.Employee;

public interface EmployeeRepository {
	
	public List<Employee> getAllEmployees();

	public void addEmployee(Employee employee);

	public void updateEmployee(Employee employee);
}
