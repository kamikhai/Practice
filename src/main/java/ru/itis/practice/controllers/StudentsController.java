package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.practice.services.JobProfileService;
import ru.itis.practice.services.StudentService;
import ru.itis.practice.services.TagService;

@Controller
@AllArgsConstructor
public class StudentsController {

    private final StudentService studentService;
    private final TagService tagService;
    private final JobProfileService jobProfileService;

    @GetMapping("/students")
    public String getMain(ModelMap map) {
        map.put("students", studentService.getAll());
        map.put("tags", tagService.getAll());
        map.put("profiles", jobProfileService.getAll());

        return "students";
    }
}
