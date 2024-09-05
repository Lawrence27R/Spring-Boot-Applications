package com.aurionpro.dboperations.repository;

import java.util.List;

import com.aurionpro.dboperations.entity.Student;

public interface StudentRepository {
	
	List<Student> getAllStudents();
	Student getStudent(int rollnumber);
	void addStudent(Student student);
	void upadateStudent(Student student);
	List<Student> getStudentByName(String name);
}
