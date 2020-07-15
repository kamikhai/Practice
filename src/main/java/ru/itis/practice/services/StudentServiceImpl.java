package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.dto.StudentInfoDto;
import ru.itis.practice.dto.StudentProfileInfo;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.Tag;
import ru.itis.practice.models.User;
import ru.itis.practice.repositories.CompetenceRepository;
import ru.itis.practice.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    public StudentProfileInfo getProfileInfoByUser(User user) {
        Student student = findByEmail(user.getEmail());
        List<Competence> studentCompetences = competenceRepository.findAllByStudent_Id(student.getId());
        return StudentProfileInfo.from(student, studentCompetences);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    private StudentInfoDto toInfoDto(Student student) {
        Set<String> tags = competenceRepository.findAllByStudent_IdAndConfirmedByIsNotNull(student.getId())
                .stream()
                .flatMap(c -> c.getTags().stream())
                .map(Tag::getName)
                .collect(Collectors.toSet());

        return StudentInfoDto.from(student, tags);
    }

    @Override
    @Transactional
    public List<StudentInfoDto> getAll() {
        return studentRepository.findAllByOrderByUser_FullName().stream()
                .map(this::toInfoDto)
                .collect(Collectors.toList());
    }
}
