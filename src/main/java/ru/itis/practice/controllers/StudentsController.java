package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.practice.services.StudentService;

@Controller
@AllArgsConstructor
public class StudentsController {

    private final StudentService studentService;

    @GetMapping("/students")
    public String getMain(ModelMap map) {
        map.put("students", studentService.getAll());
        return "students";
    }
}
