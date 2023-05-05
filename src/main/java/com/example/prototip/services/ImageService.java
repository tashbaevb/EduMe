package com.example.prototip.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveToTheFileSystem(MultipartFile image) throws IOException;
}
