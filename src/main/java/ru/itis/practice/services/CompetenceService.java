package ru.itis.practice.services;

import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Teacher;

public interface CompetenceService {
    Competence confirm(Long compId, Teacher teacher);
}
