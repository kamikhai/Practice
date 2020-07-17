package ru.itis.practice.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public String save(MultipartFile file, Long id);
    public Resource get(String fileName);
}
