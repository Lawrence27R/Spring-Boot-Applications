package com.aurionpro.mappings.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InstructorDto {
	private int instructorId;
	private String name;
	private String email;
	private String qualification;
	private List<String> course;
}
