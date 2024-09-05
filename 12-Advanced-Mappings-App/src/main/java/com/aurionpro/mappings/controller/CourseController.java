package com.aurionpro.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.service.CourseService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/studentsApp")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.addCourse(courseDto));
    }

    @GetMapping("/courses")
    public ResponseEntity<PageResponseDto<CourseDto>> getAllCourses(
            @RequestParam int pageNumber, 
            @RequestParam int pageSize) {
        return ResponseEntity.ok(courseService.getallCourse(pageNumber, pageSize));
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable int courseId) {
        Course courseDto = courseService.getCourseByID(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id "));
        return ResponseEntity.ok(courseDto);
    }

    
    @PutMapping("/courses/instructors")
    public ResponseEntity<Course> allocateInstructorToCourse(@RequestParam int courseId, @RequestParam int instructorId){
        return ResponseEntity.ok(courseService.courseToInstructor(courseId, instructorId));
    }
    
    @PutMapping("/courses/students")
    public ResponseEntity<Void> assignStudentsToCourse(
            @RequestParam int courseId,
            @RequestBody List<Integer> rollnumbers) {
        courseService.assignStudent(courseId, rollnumbers);
        return ResponseEntity.noContent().build();
    }


}
