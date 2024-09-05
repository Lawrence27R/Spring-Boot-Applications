package com.aurionpro.main.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.aurionpro.main.dto.FileModel;
import com.aurionpro.main.entity.File;

public interface FileService {

    ResponseEntity<Map<String, Object>> uploadFile(FileModel fileModel);

    ResponseEntity<File> getFileUrl(int fileId);
}
