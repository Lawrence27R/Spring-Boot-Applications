package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Student;
import com.aurionpro.dboperations.exception.StudentDoesNotExistException;
import com.aurionpro.dboperations.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public Optional<Student> getStudentById(int rollnumber) {
        Optional<Student> student = studentRepo.findById(rollnumber);
        if (!student.isPresent()) {
            throw new StudentDoesNotExistException(rollnumber);
        }
        return student;
    }

    @Override
    public ResponsePageDto getAllStudents(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Student> studentPage = studentRepo.findAll(pageable);
        ResponsePageDto studentPageDto = new ResponsePageDto();
        studentPageDto.setTotalPages(studentPage.getTotalPages());
        studentPageDto.setTotalElements(studentPage.getTotalElements());
        studentPageDto.setSize(studentPage.getSize());
        studentPageDto.setContents(studentPage.getContent());
        studentPageDto.setLastPage(studentPage.isLast());
        return studentPageDto;
    }
    
    @Override
    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }
}
