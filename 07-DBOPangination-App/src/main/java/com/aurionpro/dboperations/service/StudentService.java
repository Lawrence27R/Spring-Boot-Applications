package com.aurionpro.dboperations.service;

import java.util.Optional;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Student;

public interface StudentService {

    void addStudents(Student student);

    Optional<Student> getStudentById(int rollnumber);

    void updateStudent(Student student);

    ResponsePageDto getAllStudents(int pageSize, int pageNumber);

    ResponsePageDto getStudentByName(String name, int pageSize, int pageNumber);
}
