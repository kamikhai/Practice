package ru.itis.practice.services;

import org.springframework.stereotype.Service;
import ru.itis.practice.models.Student;
import ru.itis.practice.repositories.StudentRepository;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itis.practice.dto.StudentProfileInfo;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.User;
import ru.itis.practice.repositories.CompetenceRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private CompetenceRepository competenceRepository;

    @Override
    public Student findByEmail(String email) {
        try {
            Optional<Student> studentCandidate = studentRepository.findStudentByUser_Email(email);
            if (studentCandidate.isPresent()) {
                return studentCandidate.get();
            }
            throw new EmptyResultDataAccessException(-1);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("No student found!");
        }
    }

    @Override
    public StudentProfileInfo getProfileInfoByUser(User user) {
        Student student = findByEmail(user.getEmail());
        List<Competence> studentCompetences = competenceRepository.findAllByStudent_Id(student.getId());
        return StudentProfileInfo.from(student, studentCompetences);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
