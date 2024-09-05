package com.aurionpro.main.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileModel {

    private String name;
    private MultipartFile file;
}
	