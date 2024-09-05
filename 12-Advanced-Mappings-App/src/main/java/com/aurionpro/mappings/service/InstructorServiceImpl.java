package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.InstructorDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.entity.Instructor;
import com.aurionpro.mappings.repository.CourseRepository;
import com.aurionpro.mappings.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepo;
    
    @Autowired
    private CourseRepository courseRepo;

	@Override
	public InstructorDto addInstuctor(InstructorDto instructorDto) {
		Instructor instructor = instructorRepo.save(toInstructorMapper(instructorDto));
		return toInstructorDtoMapper(instructor);
	}
	
	public Instructor toInstructorMapper(InstructorDto instructorDto) {
		
		Instructor instructor = new  Instructor() ;
		
		instructor.setName(instructorDto.getName());
		instructor.setEmail(instructorDto.getEmail());
		instructor.setQualification(instructorDto.getQualification());
		
		return instructor ;
		
	}
	
	public InstructorDto toInstructorDtoMapper(Instructor instructor) {
		
		InstructorDto instructorDto = new InstructorDto() ;
		
		instructorDto.setName(instructor.getName());
		instructorDto.setEmail(instructor.getEmail());
		instructorDto.setInstructorId(instructor.getInstructorId());
		instructorDto.setQualification(instructor.getQualification());
		
		return instructorDto ;
	}

	@Override
	public PageResponseDto<InstructorDto> getAllInstructor(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Instructor> instructorPage = instructorRepo.findAll(pageable);

        List<InstructorDto> instructorDtoList = new ArrayList<>();
        for (Instructor instructor : instructorPage.getContent()) {
            instructorDtoList.add(toInstructorDtoMapper(instructor));
        }

        PageResponseDto<InstructorDto> instructorPageDto = new PageResponseDto<>();
        instructorPageDto.setTotalPages(instructorPage.getTotalPages());
        instructorPageDto.setTotalElements(instructorPage.getTotalElements());
        instructorPageDto.setSize(instructorPage.getSize());
        instructorPageDto.setContents(instructorDtoList);
        instructorPageDto.setLastPage(instructorPage.isLast());

        return instructorPageDto;
	}

	@Override
	public InstructorDto getInstructorById(int id) {
		Optional<Instructor> instructorData =  instructorRepo.findById(id);
		
		InstructorDto instructorDto = new InstructorDto() ;
		
		List<String> courseName = new ArrayList<>() ;
		for(Course course : instructorData.get().getCousrses()) {
			courseName.add(course.getName());
		}
		
		instructorDto.setInstructorId(instructorData.get().getInstructorId());
		instructorDto.setName(instructorData.get().getName());
		instructorDto.setEmail(instructorData.get().getEmail());
		instructorDto.setQualification(instructorData.get().getQualification());
		instructorDto.setCourse(courseName);
		
		return instructorDto ;
	}

	@Override
	public Instructor allocateCourse(int instructorId, List<Integer> courseId) {
		Instructor dbInstructor = toInstructorMapper(getInstructorById(instructorId));
		dbInstructor.setInstructorId(instructorId);
		List<Course> instructorCourse = dbInstructor.getCousrses() ;
		if(instructorCourse == null) {
			instructorCourse = new ArrayList<>() ;
		}
		
		List<Course> courseToadd = new ArrayList<>() ;
		
		
		courseId.forEach((courseIds)->{
			Optional<Course> course = courseRepo.findById(courseIds) ;
			if(course.isEmpty()) {
				throw new NullPointerException("Course does'nt exist ") ;
			}
			
		   Course addCourse = course.get() ;
		   addCourse.setInstructor(dbInstructor);
		   courseToadd.add(addCourse);
			
			
		});
		
		instructorCourse.addAll(courseToadd);
		dbInstructor.setCousrses(instructorCourse);
		
		return instructorRepo.save(dbInstructor);
		
	}

}
