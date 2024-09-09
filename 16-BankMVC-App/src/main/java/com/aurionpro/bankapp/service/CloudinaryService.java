package com.aurionpro.bankapp.service;

import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;

public interface CloudinaryService {
	String uploadDocument(MultipartFile document) throws IOException;
}
