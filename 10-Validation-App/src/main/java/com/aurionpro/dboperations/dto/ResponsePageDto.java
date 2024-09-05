package com.aurionpro.dboperations.dto;

import java.util.List;

import com.aurionpro.dboperations.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ResponsePageDto {
	
	private long totalElements;
	private int totalPages;
	private int size;
	private List<Student> contents;
	private boolean isLastPage;
}
