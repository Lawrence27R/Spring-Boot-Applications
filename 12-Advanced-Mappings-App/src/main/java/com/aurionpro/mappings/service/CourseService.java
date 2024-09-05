package com.aurionpro.mappings.service;

import java.util.List;
import java.util.Optional;

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.entity.Course;

public interface CourseService {

	 CourseDto addCourse(CourseDto courseDto);
	 PageResponseDto<CourseDto>  getallCourse(int pageNumber , int  pageSize) ;
	 Optional<Course> getCourseByID(int courseId);
	 Course courseToInstructor(int courseId , int  instructorId);
	 
	public void assignStudent(int courseId , List<Integer> rollNumber) ;
}
