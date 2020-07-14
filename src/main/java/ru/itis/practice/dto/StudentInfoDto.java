package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Student;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class StudentInfoDto {

    private Long id;
    private String imgUrl;
    private String fullName;
    private String description;
    private List<String> competences;

    public static StudentInfoDto from(Student student) {
        List<String> competences = student.getCompetences().stream()
                .filter(c -> c.getConfirmedBy() != null)
                .map(Competence::getDescription)
                .collect(Collectors.toList());

        return builder()
                .id(student.getId())
                .imgUrl(student.getUser().getPhotoPath())
                .fullName(student.getUser().getFullName())
                .description(student.getDescription())
                .competences(competences)
                .build();
    }
}
