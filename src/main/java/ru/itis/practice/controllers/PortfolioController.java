package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.practice.dto.NewProjectDto;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.ProjectService;
import ru.itis.practice.services.StudentService;
import ru.itis.practice.services.TagService;

@Controller
@AllArgsConstructor
public class PortfolioController {

    private StudentService studentService;
    private ProjectService projectService;
    private TagService tagService;


    @GetMapping("/portfolio/{id}")
    public String getPortfolio(@PathVariable Long id,
                                Model model,
                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        model.addAttribute("projects", projectService.getProjectsByStudentId(id));
        model.addAttribute("userInfo", studentService.getPortfolioInfo(id, userDetails));
        model.addAttribute("tags", tagService.getAll());
        return "portfolio";
    }

    @PostMapping("/portfolio")
    @PreAuthorize(value = "hasAuthority('STUDENT')")
    public ResponseEntity<String> confirmCompetence(NewProjectDto dto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        projectService.save(dto, userDetails.getUser());
        return ResponseEntity.ok("Запись успешно добавлена");
    }
}
