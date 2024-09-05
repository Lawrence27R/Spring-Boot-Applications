package com.aurionpro.dboperations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
	
	@Column(name = "employeeId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	
	@Column
	private String employeeName;
	@Column
	private String deparament;
	@Column
	private double salary;
	
	
	public Employee() {
		super();
	}


	public Employee(int employeeId, String employeeName, String deparament, double salary) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.deparament = deparament;
		this.salary = salary;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getDeparament() {
		return deparament;
	}


	public void setDeparament(String deparament) {
		this.deparament = deparament;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", deparament=" + deparament
				+ ", salary=" + salary + "]";
	}
	
	
}
