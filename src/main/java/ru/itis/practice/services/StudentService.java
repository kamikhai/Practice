package ru.itis.practice.services;

import ru.itis.practice.dto.ProfileInfo;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.User;

public interface StudentService {
    Student findByEmail(String email);
    ProfileInfo getProfileInfoByUser(User user);
}
