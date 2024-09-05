package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Student;
import com.aurionpro.dboperations.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentrepo;

    @Override
    public void addStudents(Student student) {
        studentrepo.save(student);
    }

    @Override
    public Optional<Student> getStudentById(int rollnumber) {
        return studentrepo.findById(rollnumber);
    }

    @Override
    public void updateStudent(Student student) {
        studentrepo.save(student);
    }

    @Override
    public ResponsePageDto getAllStudents(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        
        Page<Student> studentPage = studentrepo.findAll(pageable);
        ResponsePageDto studentPageDto = new ResponsePageDto();
        studentPageDto.setTotalPages(studentPage.getTotalPages());
        studentPageDto.setTotalElements(studentPage.getTotalElements());
        studentPageDto.setSize(studentPage.getSize());
        studentPageDto.setContents(studentPage.getContent());
        studentPageDto.setLastPage(studentPage.isLast());
        return studentPageDto;
    }

    @Override
    public ResponsePageDto getStudentByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Student> studentPage = studentrepo.findByName(name, pageable);

        ResponsePageDto studentPageDto = new ResponsePageDto();
        studentPageDto.setTotalPages(studentPage.getTotalPages());
        studentPageDto.setTotalElements( studentPage.getTotalElements()); 
        studentPageDto.setSize(studentPage.getSize());
        studentPageDto.setContents(studentPage.getContent());
        studentPageDto.setLastPage(studentPage.isLast());

        return studentPageDto;
    }
}
