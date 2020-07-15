package ru.itis.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.models.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeacherProfileInfo {

    private String fullname;
	private String position;
	private String information;
	private List<GroupInfo> groups;
	private List<String> links;
	private User user;
	private String photoPath;

	public static TeacherProfileInfo from(Teacher teacher) {
		return TeacherProfileInfo.builder()
				.fullname(teacher.getUser().getFullName())
				.information(teacher.getInformation())
				.position(teacher.getPosition())
				.groups(GroupInfo.from(teacher.getCuratedGroups()))
				.links(Collections.singletonList("vk.com/id0"))
				.user(teacher.getUser())
				.photoPath(teacher.getUser().getPhotoPath())
				.build();
	}

	public static List<TeacherProfileInfo> from(List<Teacher> teachers) {
		return teachers.stream().map(TeacherProfileInfo::from).collect(Collectors.toList());
	}

}
