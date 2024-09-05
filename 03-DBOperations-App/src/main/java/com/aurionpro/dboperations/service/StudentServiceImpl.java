package com.aurionpro.dboperations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.entity.Student;
import com.aurionpro.dboperations.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public List<Student> getAllStudents() {

		return studentRepo.getAllStudents();
	}

	@Override
	public Student getStudent(int rollnumber) {
		return studentRepo.getStudent(rollnumber);
	}

	@Override
	public void addStudent(Student studnet) {
		studentRepo.addStudent(studnet);
		
	}

	@Override
	public void updateStudent(Student student) {
		studentRepo.upadateStudent(student);
		
	}

	@Override
	public List<Student> getStudentByName(String name) {
		return studentRepo.getStudentByName(name);
	}

}
