package ru.itis.practice.services;

import ru.itis.practice.dto.TeacherProfileInfo;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.models.User;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Teacher findByEmail(String email);
    TeacherProfileInfo getProfileInfoByUser(User user);
    List<TeacherProfileInfo> getAllTeachers();

    Teacher save(Teacher teacher);

    Optional<Teacher> findById(Long id);
}
