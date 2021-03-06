package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.practice.dto.TeacherProfileInfo;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.User;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.*;

@Controller
@AllArgsConstructor
public class TeacherGroupsController {

    private TeacherService teacherService;
    private StudentService studentService;
    private GroupService groupService;
    private UserService userService;
    private TokenService tokenService;

    @GetMapping("/my_students/{id}")
    private String getStudents(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model, @PathVariable Long id) {
        TeacherProfileInfo teacherProfileInfo = teacherService.getProfileInfoByUser(userService.getUserById(id));
        if (userDetails != null) {
            model.addAttribute("isOwnProfile", userDetails.getUser().getId().equals(id));
        } else {
            model.addAttribute("isOwnProfile", false);

        }
        model.addAttribute("id", id);
        model.addAttribute("groups", teacherProfileInfo.getGroups());
        model.addAttribute("photo", teacherProfileInfo.getPhotoPath());
        return "teachergroups";
    }

    @GetMapping("/students_group")
    private String getStudents(@RequestParam(value = "g", required = false) Long groupId,
                               ModelMap map, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            map.put("admin", userDetails.getUser().getRole().equals(User.Role.ADMIN));
            map.put("token", tokenService.getToken(userDetails.getUser()));

        } else {
            map.put("admin", false);
            map.put("token", "");
        }
        map.put("students", studentService.getAllByGroupId(groupId));
        map.put("group", groupService.findById(groupId).get());
        return "group";
    }
}
