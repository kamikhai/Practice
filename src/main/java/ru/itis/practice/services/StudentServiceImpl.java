package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.dto.PortfolioUserInfo;
import ru.itis.practice.dto.StudentInfoDto;
import ru.itis.practice.dto.StudentProfileInfo;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.Tag;
import ru.itis.practice.models.User;
import ru.itis.practice.repositories.CompetenceRepository;
import ru.itis.practice.repositories.StudentRepository;
import ru.itis.practice.security.details.UserDetailsImpl;

import java.util.ArrayList;
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

	@Override
	@Transactional
	public List<StudentInfoDto> getAll(List<Long> tags, List<Long> profiles) {
		List<Student> students = studentRepository.findAllByOrderByUser_FullName();
		List<StudentInfoDto> result = new ArrayList<>();

		for (Student student : students) {
			Set<Tag> competenceTags = competenceRepository
					.findAllByStudent_IdAndConfirmedByIsNotNull(student.getId())
					.stream()
					.flatMap(c -> c.getTags().stream())
					.collect(Collectors.toSet());

			boolean tagsOK = tags == null || tags.isEmpty() || competenceTags.stream()
					.map(Tag::getId)
					.collect(Collectors.toSet())
					.containsAll(tags);

			boolean profOK = profiles == null || profiles.isEmpty() ||
					(student.getJobProfile() != null && profiles.contains(student.getJobProfile().getId()));

			if (tagsOK && profOK) {
				result.add(StudentInfoDto.from(student, competenceTags));
			}
		}
         return result;
    }

    @Override
    public List<StudentInfoDto> getAllByGroupId(Long groupId) {
        List<Student> students = studentRepository.findAllByGroup_Id(groupId);
        List<StudentInfoDto> result = new ArrayList<>();

        for (Student student : students) {
            Set<Tag> competenceTags = competenceRepository
                    .findAllByStudent_IdAndConfirmedByIsNotNull(student.getId())
                    .stream()
                    .flatMap(c -> c.getTags().stream())
                    .collect(Collectors.toSet());
                result.add(StudentInfoDto.from(student, competenceTags));
        }

        return result;
    }

	@Override
	@Transactional
	public void updateDescription(Long id, String description) {
		studentRepository.updateDescription(id, description);
	}

    @Override
    public PortfolioUserInfo getPortfolioInfo(Long id, UserDetailsImpl possibleUser) {
        Optional<Student> studentCandidate = studentRepository.findById(id);
        if (studentCandidate.isPresent()) {
            Boolean isLoggedUser = possibleUser != null && possibleUser.getUser().getId().equals(id);
            return PortfolioUserInfo.from(studentCandidate.get(), isLoggedUser);
        }
        throw new RuntimeException("No student found!");
    }
}
