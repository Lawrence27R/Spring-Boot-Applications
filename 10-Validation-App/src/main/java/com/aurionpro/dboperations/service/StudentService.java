package com.aurionpro.dboperations.service;

import java.util.Optional;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Student;

public interface StudentService {

    Optional<Student> getStudentById(int rollnumber);

    ResponsePageDto getAllStudents(int pageSize, int pageNumber);

	Student addStudent(Student student);
}
