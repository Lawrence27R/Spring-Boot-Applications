package com.aurionpro.dboperations.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Student;
import com.aurionpro.dboperations.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentApp")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{rollnumber}")
    public ResponseEntity<Optional<Student>> getStudent(@PathVariable int rollnumber) {
        return ResponseEntity.ok(studentService.getStudentById(rollnumber));
    }

    @GetMapping("/students")
    public ResponseEntity<ResponsePageDto> getStudents(
            @RequestParam int pageSize,
            @RequestParam int pageNumber) {
        return ResponseEntity.ok(studentService.getAllStudents(pageSize, pageNumber));
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
}
