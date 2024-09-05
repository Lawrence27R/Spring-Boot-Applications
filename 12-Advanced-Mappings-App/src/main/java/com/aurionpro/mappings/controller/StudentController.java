package com.aurionpro.mappings.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.StudentDto;
import com.aurionpro.mappings.entity.Address;
import com.aurionpro.mappings.entity.Student;
import com.aurionpro.mappings.service.StudentService;

@RestController
@RequestMapping("/studentsApp")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/students")
    public ResponseEntity<PageResponseDto<StudentDto>> getAllStudents(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {

        PageResponseDto<StudentDto> response = studentService.getAllStudents(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{rollnumber}")
    public ResponseEntity<StudentDto> getStudentByRollNumber(@PathVariable int rollnumber) {
        StudentDto studentDto = studentService.getStudentByRollNumber(rollnumber);
        return ResponseEntity.ok(studentDto);
    }

    @PutMapping("/student/address")
    public ResponseEntity<String> updateStudentAddress(@RequestParam int rollnumber, @RequestBody Address address) {
        studentService.updatedAddress(address, rollnumber);
        return ResponseEntity.ok("Address updated successfully");
    }

    @PutMapping("/student/course")
    public ResponseEntity<StudentDto> assignCourses(@RequestBody List<Integer> courseIds, @RequestParam int rollNumber) {
        StudentDto updatedStudent = studentService.assignCourse(rollNumber, courseIds);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/students/instructor")
    public ResponseEntity<Set<StudentDto>> getStudentsByInstructorId(@RequestParam int instructorId) {
        Set<StudentDto> students = studentService.getStudentByInstructor(instructorId);
        return ResponseEntity.ok(students);
    }

}
