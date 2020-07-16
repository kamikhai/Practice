package ru.itis.practice.services;

import ru.itis.practice.dto.StudentInfoDto;
import ru.itis.practice.dto.StudentProfileInfo;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.User;

import java.util.List;

public interface StudentService {
    Student findByEmail(String email);

    StudentProfileInfo getProfileInfoByUser(User user);

    Student save(Student student);

    List<StudentInfoDto> getAll(List<Long> tags, List<Long> profiles);

    List<StudentInfoDto> getAllByGroupId(Long groupId);

    void updateDescription(Long id, String description);


}
