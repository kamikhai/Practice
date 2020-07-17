package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.practice.models.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioUserInfo {

    private Long id;
    private String photoPath;
    private Boolean isOwnProfile;
    private String group;

    public static PortfolioUserInfo from(Student student, boolean isOwnProfile) {
        return PortfolioUserInfo.builder()
                .id(student.getId())
                .photoPath(student.getUser().getPhotoPath())
                .isOwnProfile(isOwnProfile)
                .group(student.getGroup().getNumeric())
                .build();
    }
}
