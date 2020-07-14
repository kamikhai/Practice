package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.dto.StudentInfoDto;
import ru.itis.practice.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentInfoServiceImpl implements StudentInfoService {

    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public List<StudentInfoDto> getAll() {
        return studentRepository.findAll().stream()
                .map(StudentInfoDto::from)
                .collect(Collectors.toList());
    }
}
