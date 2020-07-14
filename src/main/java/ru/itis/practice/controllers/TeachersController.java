package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.practice.services.TeacherService;

@Controller
@AllArgsConstructor
public class TeachersController {

    private TeacherService teacherService;

    @GetMapping("/teachers")
    @PreAuthorize(value = "isAuthenticated()")
    private String getTeachers(Model model){
        System.out.println(model);
        System.out.println(teacherService);
        //TODO: НЕ ВИДИТ БИН
        model.addAttribute("teacherList", teacherService.getAllTeachers());
        return "teachers";
    }
}
