package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.repositories.CompetenceRepository;

@Service
@AllArgsConstructor
public class CompetenceServiceImpl implements CompetenceService {

    private CompetenceRepository competenceRepository;

    @Override
    public Competence confirm(Long compId, Teacher teacher) {
        Competence competence = competenceRepository.getOne(compId);
        System.out.println(competence);
        competence.setConfirmedBy(teacher);
        System.out.println(competence);
        return competenceRepository.save(competence);
    }
}
