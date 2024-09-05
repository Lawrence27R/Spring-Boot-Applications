package com.aurionpro.mappings.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "course")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private int courseId;

    @Column
    @NotBlank(message = "Course name is mandatory")
    @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Course name must contain only letters")
    private String name;

    @Column
    @NotNull(message = "Duration is mandatory")
    @Min(value = 1, message = "Duration must be at least 1 month")
    private int duration; 

    @Column
    @NotNull(message = "Fees are mandatory")
    @Positive(message = "Fees must be a positive number")
    private double fees;

	@ManyToOne(cascade = {CascadeType.DETACH ,CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REFRESH})
	@JoinColumn(name = "instructorID")
	private Instructor instructor ;
	
	@ManyToMany(mappedBy = "courses")
	private List<Student> student ;
}
