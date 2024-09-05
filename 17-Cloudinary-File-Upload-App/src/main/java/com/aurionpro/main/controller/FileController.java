package com.aurionpro.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.main.dto.FileModel;
import com.aurionpro.main.entity.File;
import com.aurionpro.main.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(@RequestParam String name,
                                                      @RequestParam MultipartFile file) {
        FileModel fileModel = new FileModel();
        fileModel.setName(name);
        fileModel.setFile(file);
        return fileService.uploadFile(fileModel);
    }

    @GetMapping("/url/{fileId}")
    public ResponseEntity<File> getFileUrl(@PathVariable int fileId) {
        return fileService.getFileUrl(fileId);
    }

}
