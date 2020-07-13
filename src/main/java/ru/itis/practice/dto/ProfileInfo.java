package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileInfo {

    private String photoPath;

    private String fullName;

    private String jobProfileTitle;

    private String description;

    private List<CompetenceProfileInfo> competenceList;

    private String groupNumeric;

    public static ProfileInfo from(Student student, List<Competence> competences) {
        User user = student.getUser();
        ProfileInfo info =  ProfileInfo.builder()
                .photoPath(user.getPhotoPath())
                .fullName(user.getFullName())
                .jobProfileTitle(student.getJobProfile().getTitle())
                .description(student.getDescription())
                .competenceList(new ArrayList<>())
                .groupNumeric(student.getGroup().getNumeric())
                .build();
        if (competences != null) competences.stream().map(CompetenceProfileInfo::from).forEach(el -> info.competenceList.add(el));
        return info;
    }
}
