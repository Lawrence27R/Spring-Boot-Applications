package com.aurionpro.dboperations.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PageResponseDto<T> {

    private long totalElements;
    private int totalPages;
    private int size;
    private List<T> contents;
    private boolean isLastPage;
}
