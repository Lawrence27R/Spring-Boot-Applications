package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.StudentDto;
import com.aurionpro.mappings.entity.Address;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.entity.Instructor;
import com.aurionpro.mappings.entity.Student;
import com.aurionpro.mappings.repository.CourseRepository;
import com.aurionpro.mappings.repository.InstructorRepository;
import com.aurionpro.mappings.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private InstructorRepository instructorRepo;

	@Override
	public Student addStudent(Student student) {
		return studentRepo.save(student);
	}

	private StudentDto toStudentDtoMapper(Student student) {
		StudentDto studentDto = new StudentDto();

		studentDto.setAge(student.getAge());
		studentDto.setName(student.getName());
		studentDto.setRollnumber(student.getRollnumber());

		return studentDto;
	}

	private Student toStudentMapper(StudentDto studentDto) {
		Student student = new Student();

		student.setAge(studentDto.getAge());
		student.setName(studentDto.getName());
		if (student.getRollnumber() > 0)
			student.setRollnumber(studentDto.getRollnumber());

		return student;
	}

	@Override
	public Address getStudentAddress(int rollnumber) {
	    Optional<Student> studentOptional = studentRepo.findById(rollnumber);
	    
	    if (studentOptional.isEmpty()) {
	        throw new EntityNotFoundException("Student not found with rollnumber ");
	    }
	    Student student = studentOptional.get();
	    Address address = student.getAddress();
	    
	    if (address == null) {
	        throw new EntityNotFoundException("Address not found for student with rollnumber ");
	    }
	    
	    return address;
	}


    @Override
    public void updatedAddress(Address newAddress, int rollnumber) {
        Student student = studentRepo.findById(rollnumber)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with rollnumber "));

        Address dbAddress = student.getAddress();
        if (dbAddress != null) {
            newAddress.setAddressId(dbAddress.getAddressId());
        }
        student.setAddress(newAddress);
        studentRepo.save(student);
    }

	@Override
	public StudentDto getStudentByRollNumber(int rollnumber) {
		Optional<Student> student = studentRepo.findById(rollnumber);
		Student studentdb = student.get();
		return toStudentDtoMapper(studentdb);
	}

	@Override
	public PageResponseDto<StudentDto> getAllStudents(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Student> studentPage = studentRepo.findAll(pageable);
		PageResponseDto<StudentDto> studentResponseDto = new PageResponseDto<>();

		studentResponseDto.setTotalPages(studentPage.getTotalPages());
		studentResponseDto.setTotalElements(studentPage.getTotalElements());
		studentResponseDto.setSize(studentPage.getSize());
//        studentResponseDto.setContents(studentPage.getContent());
		List<StudentDto> studentDtos = new ArrayList<>();
		for (Student student : studentPage.getContent()) {
			studentDtos.add(toStudentDtoMapper(student));
		}
		studentResponseDto.setContents(studentDtos);
		studentResponseDto.setLastPage(studentPage.isLast());
		return studentResponseDto;
	}


	@Override
	public StudentDto assignCourse(int rollnumber, List<Integer> courseIds) {
		Student student = studentRepo.findById(rollnumber)
				.orElseThrow(() -> new EntityNotFoundException("Student not found "));

		Set<Course> existingCourses = Optional.ofNullable(student.getCourses()).orElse(new HashSet<>());

		for (Integer courseId : courseIds) {
			Course course = courseRepo.findById(courseId)
					.orElseThrow(() -> new EntityNotFoundException("Course not found"));

			course.getStudent().add(student);
			existingCourses.add(course);
		}

		student.setCourses(existingCourses);
		return toStudentDtoMapper(studentRepo.save(student));
	}

	@Override
	public Set<StudentDto> getStudentByInstructor(int instructorId) {
		Instructor instructor = instructorRepo.findById(instructorId)
				.orElseThrow(() -> new NullPointerException("Instructor not found"));

		Set<StudentDto> students = new HashSet<>();
		for (Course course : instructor.getCousrses()) {
			for (Student student : course.getStudent()) {
				students.add(toStudentDtoMapper(student));
			}
		}

		return students;
	}
}

