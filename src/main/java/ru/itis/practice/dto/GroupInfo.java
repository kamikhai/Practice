package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.itis.practice.models.Group;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GroupInfo {

	private String numeric;

	public static GroupInfo from(Group group) {
		return GroupInfo.builder()
				.numeric(group.getNumeric())
				.build();
	}

	public static List<GroupInfo> from(List<Group> groups) {
		return groups.stream().map(GroupInfo::from).collect(Collectors.toList());
	}
}
