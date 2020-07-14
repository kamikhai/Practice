package ru.itis.practice.services;

import ru.itis.practice.dto.StudentInfoDto;

import java.util.List;

public interface StudentInfoService {
    List<StudentInfoDto> getAll();
}
