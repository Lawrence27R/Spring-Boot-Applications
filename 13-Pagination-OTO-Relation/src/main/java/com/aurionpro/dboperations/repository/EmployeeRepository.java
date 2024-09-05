package com.aurionpro.dboperations.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.dboperations.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Page<Employee> findByFirstNameContaining(String name, Pageable pageable);
}