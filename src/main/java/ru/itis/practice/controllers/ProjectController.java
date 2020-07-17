package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.practice.services.ProjectService;
import ru.itis.practice.services.TagService;

@Controller
@AllArgsConstructor
public class ProjectController {

    private ProjectService projectService;

    @GetMapping("/project/{id}")
    private String getProject(@PathVariable Long id,
                              Model model){
        model.addAttribute("info", projectService.getProjectById(id));
        return "project";
    }
}
