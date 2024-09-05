package com.aurionpro.dboperations.controller;

import java.util.List;
import java.util.Optional;

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

import com.aurionpro.dboperations.entity.Student;
import com.aurionpro.dboperations.service.StudentService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/studentApp")
public class StudentController {

	@Autowired
	private StudentService studentservice;
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		return ResponseEntity.ok(studentservice.getAllStudents());
	}
	
	@PostMapping("/students")
	@Transactional
	public String addStudent(@RequestBody Student student) {  
		studentservice.addStudents(student);
		return "Record Added Successfully";
	}
	
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<Optional<Student>> getStudent(@PathVariable int rollnumber){
		return ResponseEntity.ok(studentservice.getStudentById(rollnumber));
	}
	
	@PutMapping("/students")
	@Transactional
	public String updateStudent(@RequestBody Student student) {  
		studentservice.updateStudent(student);
		return "Record Updated Successfully";
	}
	
	@GetMapping("/students-name")
	public ResponseEntity<Optional<Student>> getStudent(@RequestParam String name){
		return ResponseEntity.ok(studentservice.getStudentByName(name));
	}
}
