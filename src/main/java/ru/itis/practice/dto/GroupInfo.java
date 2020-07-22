package ru.itis.practice.dto;

import lombok.*;
import ru.itis.practice.models.Group;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GroupInfo {

	private String numeric;
	private Long id;

	public static GroupInfo from(Group group) {
		return GroupInfo.builder()
				.numeric(group.getNumeric())
				.id(group.getId())
				.build();
	}

	public static List<GroupInfo> from(List<Group> groups) {
		return groups.stream().map(GroupInfo::from).collect(Collectors.toList());
	}
}
