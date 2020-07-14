package ru.itis.practice.services;

import ru.itis.practice.dto.TeacherProfileInfo;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.models.User;

public interface TeacherService {
    Teacher findByEmail(String email);
    TeacherProfileInfo getProfileInfoByUser(User user);
}
