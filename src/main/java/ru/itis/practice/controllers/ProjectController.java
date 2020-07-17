package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.ProjectService;
import ru.itis.practice.services.TagService;

@Controller
@AllArgsConstructor
public class ProjectController {

    private ProjectService projectService;

    @GetMapping("/project/{id}")
    public String getProject(@PathVariable Long id,
                              Model model,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("info", projectService.getProjectById(id));
        model.addAttribute("isAccessible", projectService.isOwner(id, userDetails));
        return "project";
    }

    @PostMapping("/project/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteProject(@PathVariable Long id,
                                        @RequestParam Long userId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        projectService.remove(id, userId, userDetails.getUser());
        return ResponseEntity.ok(200);
    }
}
