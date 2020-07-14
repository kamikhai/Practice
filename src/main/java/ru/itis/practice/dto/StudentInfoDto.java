package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.practice.models.Student;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class StudentInfoDto {
    private Long id;
    private String imgUrl;
    private String fullName;
    private String description;
    private List<String> tags;

    public static StudentInfoDto from(Student student, Set<String> tags) {
        return builder()
                .id(student.getId())
                .imgUrl(student.getUser().getPhotoPath())
                .fullName(student.getUser().getFullName())
                .description(student.getDescription())
                .tags(tags.stream().sorted().collect(Collectors.toList()))
                .build();
    }
}
