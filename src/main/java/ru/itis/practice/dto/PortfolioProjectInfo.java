package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.practice.models.Project;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioProjectInfo {

    private Long projectId;
    private String title;
    private String description;

    public static PortfolioProjectInfo from(Project project) {
        return PortfolioProjectInfo.builder()
                .projectId(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .build();
    }
}
