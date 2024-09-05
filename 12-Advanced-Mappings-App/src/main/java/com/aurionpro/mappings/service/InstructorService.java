package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.InstructorDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.entity.Instructor;

public interface InstructorService {
	
	 InstructorDto addInstuctor(InstructorDto instructorDto);
	 PageResponseDto<InstructorDto> getAllInstructor(int pageSize , int pageNumber);
	 InstructorDto getInstructorById(int id);
	 Instructor allocateCourse(int instructorId , List<Integer> courseId) ;
}
