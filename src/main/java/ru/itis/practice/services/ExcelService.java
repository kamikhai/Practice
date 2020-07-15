package ru.itis.practice.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ExcelService {
    File add(MultipartFile file, String group);
}
