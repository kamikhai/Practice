package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Tag;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.repositories.CompetenceRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompetenceServiceImpl implements CompetenceService {

    private CompetenceRepository competenceRepository;
    private TagService tagService;
    private StudentService studentService;

    @Override
    public Competence confirm(Long compId, Teacher teacher) {
        Competence competence = competenceRepository.getOne(compId);
        System.out.println(competence);
        competence.setConfirmedBy(teacher);
        System.out.println(competence);
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
}
