package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.practice.services.TagService;

@Controller
@AllArgsConstructor
public class ProjectController {

    @GetMapping("/project")
    private String getProject(ModelMap modelMap){
        return "project";
    }
}
