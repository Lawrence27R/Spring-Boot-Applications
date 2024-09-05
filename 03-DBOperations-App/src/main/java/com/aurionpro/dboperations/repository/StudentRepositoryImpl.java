package com.aurionpro.dboperations.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dboperations.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentRepositoryImpl implements StudentRepository{
	
	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Student> getAllStudents() {
		TypedQuery<Student> query = manager.createQuery("select s from Student s", Student.class);
		return query.getResultList();
	}

	@Override
	public Student getStudent(int rollnumber) {
		return manager.find(Student.class, rollnumber);
	}

	@Override
	public void addStudent(Student student) {
		manager.persist(student);  // adding to context
		
	}

	@Override
	public void upadateStudent(Student student) {
		manager.merge(student);
		
	}

	@Override
	public List<Student> getStudentByName(String name) {
		TypedQuery<Student> query = manager.createQuery("select s from Student s where s.name =: theName", Student.class);
		query.setParameter("theName", name);
		return query.getResultList();
	}

}
