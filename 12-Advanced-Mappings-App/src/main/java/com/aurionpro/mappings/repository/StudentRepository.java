package com.aurionpro.mappings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.mappings.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	Optional<Student> findByRollnumber(int rollnumber);
}
