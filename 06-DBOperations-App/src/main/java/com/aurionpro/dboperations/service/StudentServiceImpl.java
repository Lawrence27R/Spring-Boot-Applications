package com.aurionpro.dboperations.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.entity.Student;
import com.aurionpro.dboperations.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentrepo;
	
//	Student student = new Student();
//	student.
	
	@Override
	public List<Student> getAllStudents() {
		return studentrepo.findAll();
	}

	@Override
	public void addStudents(Student student) {
		studentrepo.save(student);
	
	}

	@Override
	public Optional<Student> getStudentById(int rollnumber) {
		return studentrepo.findById(rollnumber);
		
	}

	@Override
	public void updateStudent(Student student) {
		studentrepo.save(student);
		
	}

	@Override
	public Optional<Student> getStudentByName(String name) {
		return studentrepo.findByName(name);
	}

	
}
