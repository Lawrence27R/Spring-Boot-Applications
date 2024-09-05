package com.aurionpro.main.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aurionpro.main.dto.FileModel;
import com.aurionpro.main.entity.File;
import com.aurionpro.main.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private FileRepository fileRepo;

    @Override
    public ResponseEntity<Map<String, Object>> uploadFile(FileModel fileModel) {
        try {
            if (fileModel.getName() == null || fileModel.getName().isEmpty() || fileModel.getFile() == null || fileModel.getFile().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Name or file cannot be empty"));
            }

            String url = cloudinaryService.uploadFile(fileModel.getFile(), "folder_1");

            if (url == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "File upload failed"));
            }

            File file = new File();
            file.setName(fileModel.getName());
            file.setUrl(url);

            file = fileRepo.save(file);

            return ResponseEntity.ok().body(Map.of("url", url, "fileId", file.getFileId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "File upload failed"));
        }
    }

    @Override
    public ResponseEntity<File> getFileUrl(int fileId) {
        Optional<File> fileOpt = fileRepo.findById(fileId);
        if (!fileOpt.isPresent()) {
        	return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fileOpt.get());
    }

}
