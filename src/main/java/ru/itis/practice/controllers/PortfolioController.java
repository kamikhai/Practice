package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.ProjectService;
import ru.itis.practice.services.StudentService;
import ru.itis.practice.services.TagService;

@Controller
@AllArgsConstructor
public class PortfolioController {

    private StudentService studentService;
    private ProjectService projectService;

    @GetMapping("/portfolio/{id}")
    private String getPortfolio(@PathVariable Long id,
                                Model model,
                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        model.addAttribute("userInfo", studentService.getPortfolioInfo(id, userDetails));
        model.addAttribute("projects", projectService.getProjectsByStudentId(id));
        return "portfolio";
    }
}
