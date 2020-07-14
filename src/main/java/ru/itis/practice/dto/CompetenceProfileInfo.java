package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Tag;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.models.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetenceProfileInfo {

    private String description;
    private boolean isConfirmed;
    private Teacher confirmedBy;
    private List<Tag> tags;
    private Long id;

    public static CompetenceProfileInfo from(Competence competence) {
        return CompetenceProfileInfo.builder()
                .description(competence.getDescription())
                .isConfirmed(competence.getConfirmedBy() != null)
                .confirmedBy(competence.getConfirmedBy())
                .tags(competence.getTags())
                .id(competence.getId())
                .build();
    }
}
