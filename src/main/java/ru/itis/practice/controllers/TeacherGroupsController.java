package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.practice.models.Student;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.GroupService;
import ru.itis.practice.services.StudentService;
import ru.itis.practice.services.TeacherService;

@Controller
@AllArgsConstructor
public class TeacherGroupsController {

    private TeacherService teacherService;
    private StudentService studentService;
    private GroupService groupService;

    @GetMapping("/my_students")
    private String getStudents(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
            model.addAttribute("groups", teacherService.getProfileInfoByUser(userDetails.getUser()).getGroups());
            return "teachergroups";
    }

    @GetMapping("/students_group")
    private String getStudents(@RequestParam(value = "g", required = false) Long groupId,
                               ModelMap map){
        map.put("students", studentService.getAllByGroupId(groupId));
        map.put("group", groupService.findById(groupId).get().getNumeric());
        return "group";
    }
}
