package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.JobProfile;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.User;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentProfileInfo {

    private String photoPath;
    private String fullName;
    private String jobProfileTitle;
    private String description;
    private List<CompetenceProfileInfo> competenceList;
    private String groupNumeric;
    private String workExperience;
    private String link;

    public static StudentProfileInfo from(Student student, List<Competence> competences) {
        User user = student.getUser();
        JobProfile jobProfile = student.getJobProfile();
        StudentProfileInfo info =  StudentProfileInfo.builder()
                .photoPath(user.getPhotoPath())
                .fullName(user.getFullName())
                .jobProfileTitle(jobProfile != null ? jobProfile.getTitle() : null)
                .description(student.getDescription())
                .competenceList(new ArrayList<>())
                .groupNumeric(student.getGroup().getNumeric())
                .workExperience(student.getWorkExperience())
                .link(student.getLink())
                .build();
        if (competences != null) competences.stream().map(CompetenceProfileInfo::from).forEach(el -> info.competenceList.add(el));
        return info;
    }
}
