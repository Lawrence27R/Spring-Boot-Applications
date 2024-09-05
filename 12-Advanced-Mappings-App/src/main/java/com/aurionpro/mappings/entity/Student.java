package com.aurionpro.mappings.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "students")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    @Column(name = "rollnumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollnumber;

    @Column
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
//    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only alphabetic characters")
    private String name;

    @Column
    @NotNull(message = "Age is mandatory")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    @NotNull(message = "Address is mandatory")
    private Address address;
    
	@ManyToMany
	@JoinTable(name="student-courses",
	joinColumns = @JoinColumn(name = "rollnumber"),
	inverseJoinColumns = @JoinColumn(name="courseId")  )
	private Set<Course> courses ;

}
