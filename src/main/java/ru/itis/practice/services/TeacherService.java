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

<<<<<<< HEAD
	void updateLink(Long userId, String text);

	void updatePosition(Long userId, String text);

	void updateInformation(Long userId, String text);

=======
    Optional<Teacher> findById(Long id);
>>>>>>> 7b3384afdab8d0ac362e93800cb4b9e7bcabd271
}
