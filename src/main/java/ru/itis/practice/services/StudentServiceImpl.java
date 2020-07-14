package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.dto.ProfileInfo;
import ru.itis.practice.dto.StudentInfoDto;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.User;
import ru.itis.practice.repositories.CompetenceRepository;
import ru.itis.practice.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CompetenceRepository competenceRepository;

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
    public ProfileInfo getProfileInfoByUser(User user) {
        Student student = findByEmail(user.getEmail());
        List<Competence> studentCompetences = competenceRepository.findAllByStudent_Id(student.getId());
        return ProfileInfo.from(student, studentCompetences);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public List<StudentInfoDto> getAll() {
        return studentRepository.findAllByOrderByUser_FullName().stream()
                .map(StudentInfoDto::from)
                .collect(Collectors.toList());
    }
}
