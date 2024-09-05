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

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.entity.Instructor;
import com.aurionpro.mappings.entity.Student;
import com.aurionpro.mappings.repository.CourseRepository;
import com.aurionpro.mappings.repository.InstructorRepository;
import com.aurionpro.mappings.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private InstructorRepository instructorRepo;
    
    @Autowired
    private StudentRepository studentRepo;

	@Override
	public CourseDto addCourse(CourseDto courseDto) {
		Course course = new Course() ;
		
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
		course.setName(courseDto.getName());
		
		courseRepo.save(course);
		
		courseDto.setCourseId(course.getCourseId());
		courseDto.setName(course.getName());
		courseDto.setDuration(course.getDuration());
		courseDto.setFees(course.getFees());
		
		return courseDto ;
	}

	@Override
	public PageResponseDto<CourseDto> getallCourse(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Course> coursePage = courseRepo.findAll(pageable);

        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Course course : coursePage.getContent()) {
            courseDtoList.add(toCourseDtoMapper(course));
        }

        PageResponseDto<CourseDto> coursePageDto = new PageResponseDto<>();
        coursePageDto.setTotalPages(coursePage.getTotalPages());
        coursePageDto.setTotalElements(coursePage.getTotalElements());
        coursePageDto.setSize(coursePage.getSize());
        coursePageDto.setContents(courseDtoList);
        coursePageDto.setLastPage(coursePage.isLast());

        return coursePageDto;
	}

	@Override
	public Optional<Course> getCourseByID(int courseId) {
		return courseRepo.findById(courseId);
	}

	@Override
	public Course courseToInstructor(int courseId, int instructorId) {
		Optional<Course> courses = getCourseByID(courseId);
		Optional<Instructor> dbInstructor = instructorRepo.findById(instructorId);
		Instructor instructor = dbInstructor.get() ;
		
		Course coursedb = courses.get() ;
		coursedb.setInstructor(instructor);
		
		return courseRepo.save(coursedb);
	}

    @Override
    public void assignStudent(int courseId, List<Integer> rollNumbers) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        
        Set<Student> studentsToAdd = new HashSet<>();
        rollNumbers.forEach(rollNumber -> {
            Student student = studentRepo.findById(rollNumber)
                    .orElseThrow(() -> new EntityNotFoundException("Student not found"));
            student.getCourses().add(course);
            studentsToAdd.add(student);
        });
        
        course.getStudent().addAll(studentsToAdd);
        courseRepo.save(course);
    }

    private CourseDto toCourseDtoMapper(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setName(course.getName());
        courseDto.setDuration(course.getDuration());
        courseDto.setFees(course.getFees());
        return courseDto;
    }

    private Course toCourseMapper(CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDuration(courseDto.getDuration());
        course.setFees(courseDto.getFees());
        return course;
    }
}
