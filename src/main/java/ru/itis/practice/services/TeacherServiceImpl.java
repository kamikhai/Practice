package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.itis.practice.dto.TeacherProfileInfo;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.models.User;
import ru.itis.practice.repositories.TeacherRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;


    @Override
    public Teacher findByEmail(String email) {
        try {
            Optional<Teacher> teacherCandidate = teacherRepository.findByUser_Email(email);
            if (teacherCandidate.isPresent()) {
                return teacherCandidate.get();
            }
            throw new EmptyResultDataAccessException(-1);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("No teacher found!");
        }
    }

    @Override
    public TeacherProfileInfo getProfileInfoByUser(User user) {
        Teacher teacher = findByEmail(user.getEmail());
        //TODO: Create and return ProfileInfo
        return null;
    }
}
