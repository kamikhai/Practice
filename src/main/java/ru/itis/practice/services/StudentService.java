package ru.itis.practice.services;

import ru.itis.practice.dto.ProfileInfo;
import ru.itis.practice.dto.StudentInfoDto;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.User;

import java.util.List;

public interface StudentService {
    Student findByEmail(String email);
    ProfileInfo getProfileInfoByUser(User user);
    Student save(Student student);
    List<StudentInfoDto> getAll();
}
