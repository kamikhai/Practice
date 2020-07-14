package ru.itis.practice.services;

import ru.itis.practice.dto.StudentProfileInfo;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.User;

public interface StudentService {
    Student findByEmail(String email);
    StudentProfileInfo getProfileInfoByUser(User user);
    Student save(Student student);
}
