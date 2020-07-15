package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.TeacherService;

@Controller
@AllArgsConstructor
public class TeacherGroupsController {

    private TeacherService teacherService;

    @GetMapping("/my_students")
    private String getStudents(@RequestParam(value = "group", required = false) String group,
                               @AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        if (group == null){
            model.addAttribute("groups", teacherService.getProfileInfoByUser(userDetails.getUser()).getGroups());
            return "teachergroups";
        } else {
            return "group";
        }
    }
}
