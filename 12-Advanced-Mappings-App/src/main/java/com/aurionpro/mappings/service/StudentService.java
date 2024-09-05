package com.aurionpro.mappings.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.StudentDto;
import com.aurionpro.mappings.entity.Address;
import com.aurionpro.mappings.entity.Student;

@Service
public interface StudentService {

	public Student addStudent(Student student);
	public Address getStudentAddress(int rollnumber);
	public void updatedAddress(Address address , int rollnumber);

	StudentDto getStudentByRollNumber(int rollnumber);
	PageResponseDto<StudentDto> getAllStudents(int pageNumber  , int pageSize);
	StudentDto assignCourse(int rollnumber , List<Integer> courseid);
	Set<StudentDto> getStudentByInstructor(int instructorId);
}
