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

import com.aurionpro.mappings.dto.InstructorDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.entity.Instructor;
import com.aurionpro.mappings.service.InstructorService;

@RestController
@RequestMapping("/studentsApp")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/instructor")
    public ResponseEntity<InstructorDto> addInstructor(@RequestBody InstructorDto instructorDto) {
        return ResponseEntity.ok(instructorService.addInstuctor(instructorDto));
    }

    @GetMapping("/instructors")
    public ResponseEntity<PageResponseDto<InstructorDto>> getAllInstructors(
            @RequestParam int pageNumber, 
            @RequestParam int pageSize) {
        return ResponseEntity.ok(instructorService.getAllInstructor(pageNumber, pageSize));
    }

    @GetMapping("/instructors/{instructorId}")
    public ResponseEntity<InstructorDto> getInstructorById(@PathVariable int instructorId) {
        InstructorDto instructorDto = instructorService.getInstructorById(instructorId);
        return ResponseEntity.ok(instructorDto);
    }
    
    @PutMapping("/instructors/courses")
    public ResponseEntity<Instructor> allocateCoursesToInstructor(
            @RequestParam int instructorId, 
            @RequestBody List<Integer> courseIds) {
        Instructor instructor = instructorService.allocateCourse(instructorId, courseIds);
        return ResponseEntity.ok(instructor);
    }

}
