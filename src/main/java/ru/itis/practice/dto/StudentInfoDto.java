package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.Tag;

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
    private String group;


    public static StudentInfoDto from(Student student, Set<Tag> tags) {
        return builder()
                .id(student.getId())
                .imgUrl(student.getUser().getPhotoPath())
                .fullName(student.getUser().getFullName())
                .description(student.getDescription())
                .tags(tags.stream().map(Tag::getName).sorted().collect(Collectors.toList()))
                .group(student.getGroup().getNumeric())
                .build();
    }
}
