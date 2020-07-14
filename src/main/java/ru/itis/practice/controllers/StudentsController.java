package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.practice.services.StudentInfoService;

@Controller
@AllArgsConstructor
public class StudentsController {

    private final StudentInfoService studentInfoService;

    @GetMapping("/students")
    public String getMain(ModelMap map) {
        map.put("students", studentInfoService.getAll());
        return "students";
    }
}
