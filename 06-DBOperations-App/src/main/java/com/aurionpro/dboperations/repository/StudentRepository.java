package com.aurionpro.dboperations.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.dboperations.entity.Student;


public interface StudentRepository extends JpaRepository<Student, Integer> {

	public Optional<Student> findByName(String name);
}
