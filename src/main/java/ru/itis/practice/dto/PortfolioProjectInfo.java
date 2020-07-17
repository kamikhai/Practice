package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.practice.models.Project;
import ru.itis.practice.models.Tag;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioProjectInfo {

    private Long projectId;
    private String title;
    private List<Tag> tags;

    public static PortfolioProjectInfo from(Project project) {
        return PortfolioProjectInfo.builder()
                .projectId(project.getId())
                .title(project.getTitle())
                .tags(project.getTags())
                .build();
    }
}
