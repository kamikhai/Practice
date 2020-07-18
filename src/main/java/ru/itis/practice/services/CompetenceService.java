package ru.itis.practice.services;

import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Teacher;

import java.util.Optional;
import java.util.Set;

public interface CompetenceService {

    Competence confirm(Long compId, Teacher teacher);
    void save(String text, Set<String> tags, Long studentId);

    Optional<Competence> findById(Long l);
}
