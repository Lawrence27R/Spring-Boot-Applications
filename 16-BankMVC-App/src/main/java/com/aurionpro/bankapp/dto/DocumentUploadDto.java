package com.aurionpro.bankapp.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class DocumentUploadDto {

    private String name;
    private List<MultipartFile> document;
}
