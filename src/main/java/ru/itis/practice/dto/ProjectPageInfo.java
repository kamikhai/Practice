package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.practice.models.Project;
import ru.itis.practice.models.Tag;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectPageInfo {

    private Long userId;
    private String photoPath;
    private String title;
    private String description;
    private List<Tag> tags;

    public static ProjectPageInfo from(Project project) {
        return ProjectPageInfo.builder()
                .userId(project.getStudent().getId())
                .photoPath(project.getStudent().getUser().getPhotoPath())
                .title(project.getTitle())
                .description(project.getDescription())
                .tags(project.getTags())
                .build();
    }
}
