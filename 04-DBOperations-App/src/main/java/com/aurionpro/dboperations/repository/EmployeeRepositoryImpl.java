package com.aurionpro.dboperations.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dboperations.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
	
	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> query = manager.createQuery("select s from Employee s", Employee.class);
		return query.getResultList();
	}

	@Override
	public void addEmployee(Employee employee) {
		manager.persist(employee);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		manager.merge(employee); 
		
	}

}
