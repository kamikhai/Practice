package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Tag;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.repositories.CompetenceRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompetenceServiceImpl implements CompetenceService {

    private CompetenceRepository competenceRepository;
    private TagService tagService;
    private StudentService studentService;

    @Transactional
    @Override
    public Competence confirm(Long compId, Teacher teacher) {
        Competence competence = competenceRepository.getOne(compId);
        competence.setConfirmedBy(teacher);
        return competenceRepository.save(competence);
    }

	@Override
	public void save(String text, Set<String> tagNames, Long studentId) {
    	List<Tag> tags = tagNames.stream().map(tagService::getByName).collect(Collectors.toList());
		competenceRepository.save(Competence.builder()
				.description(text)
				.tags(tags)
				.student(studentService.getById(studentId))
				.build());
	}

    @Override
    public Optional<Competence> findById(Long l) {
        return competenceRepository.findById(l);
    }
}
