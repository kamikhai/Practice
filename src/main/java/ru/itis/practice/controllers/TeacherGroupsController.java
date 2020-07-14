package ru.itis.practice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeacherGroupsController {

    @GetMapping("/my_students")
    private String getStudents(@RequestParam(value = "group", required = false) String group){
        if (group == null){
            return "teachergroups";
        } else {
            return "group";
        }
    }
}
