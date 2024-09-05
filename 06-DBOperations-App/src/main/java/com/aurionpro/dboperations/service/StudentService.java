package com.aurionpro.dboperations.service;

import java.util.List;
import java.util.Optional;

import com.aurionpro.dboperations.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();
	
	public void addStudents(Student student);
	
	public Optional<Student> getStudentById(int rollnumber);
	public void updateStudent(Student student);
	
	public Optional<Student> getStudentByName(String name);
}
