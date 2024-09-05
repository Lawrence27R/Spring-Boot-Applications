package com.aurionpro.dboperations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.dboperations.entity.Salary;


public interface SalaryRepository extends JpaRepository<Salary, Integer>{

}
