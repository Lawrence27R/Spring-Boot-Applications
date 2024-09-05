package com.aurionpro.mappings.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponseDto<T> {
    private long totalElements;
    private int totalPages;
    private int size;
    private List<T> contents;
    private boolean isLastPage;
}
