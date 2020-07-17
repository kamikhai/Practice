package ru.itis.practice.dto;

import lombok.*;
import ru.itis.practice.models.Tag;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class NewCompetenceDto {

	private String description;
	private List<Tag> tags;
}
