package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.practice.models.Competence;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetenceProfileInfo {

    private String description;

    private boolean isConfirmed;

    public static CompetenceProfileInfo from(Competence competence) {
        return CompetenceProfileInfo.builder()
                .description(competence.getDescription())
                .isConfirmed(competence.getConfirmedBy() != null)
                .build();
    }
}
