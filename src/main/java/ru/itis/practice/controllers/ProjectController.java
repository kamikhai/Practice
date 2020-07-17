package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.ProjectService;
import ru.itis.practice.services.TagService;

@Controller
@AllArgsConstructor
public class ProjectController {

    private ProjectService projectService;

    @GetMapping("/project/{id}")
    private String getProject(@PathVariable Long id,
                              Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        model.addAttribute("id", id);
        model.addAttribute("userId", userDetails.getUser().getId());
        model.addAttribute("info", projectService.getProjectById(id));
        return "project";
    }

    @PostMapping("/project/{id}")
    private String deleteProject(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return "ok";
    }
}
