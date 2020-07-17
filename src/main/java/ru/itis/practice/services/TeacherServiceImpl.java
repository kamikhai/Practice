package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.itis.practice.dto.TeacherProfileInfo;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.models.User;
import ru.itis.practice.repositories.TeacherRepository;

import java.util.List;
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
        return TeacherProfileInfo.from(findByEmail(user.getEmail()));
    }

    @Override
    public List<TeacherProfileInfo> getAllTeachers() {
        return TeacherProfileInfo.from(teacherRepository.findAll());
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

<<<<<<< HEAD
	@Override
	public void updateLink(Long userId, String text) {
        Optional<Teacher> teacherCandidate = teacherRepository.findById(userId);
        if(teacherCandidate.isPresent()) {
            Teacher teacher = teacherCandidate.get();
            teacher.setLink(text);
            teacherRepository.save(teacher);
        }
	}

    @Override
    public void updatePosition(Long userId, String text) {
        Optional<Teacher> teacherCandidate = teacherRepository.findById(userId);
        if(teacherCandidate.isPresent()) {
            Teacher teacher = teacherCandidate.get();
            teacher.setPosition(text);
            teacherRepository.save(teacher);
        }
    }

    @Override
    public void updateInformation(Long userId, String text) {
        Optional<Teacher> teacherCandidate = teacherRepository.findById(userId);
        if(teacherCandidate.isPresent()) {
            Teacher teacher = teacherCandidate.get();
            teacher.setInformation(text);
            teacherRepository.save(teacher);
        }
=======
    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
>>>>>>> 7b3384afdab8d0ac362e93800cb4b9e7bcabd271
    }
}
