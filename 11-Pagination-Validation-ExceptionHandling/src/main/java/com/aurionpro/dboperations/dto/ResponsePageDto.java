package com.aurionpro.dboperations.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResponsePageDto<T> {
	
	private long totalElements;
	private int totalPages;
	private int size;
	private List<T> contents;
	private boolean isLastPage;
}

