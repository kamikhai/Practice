package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.ImageService;
import ru.itis.practice.services.ProjectService;
import ru.itis.practice.services.StudentService;
import ru.itis.practice.services.TagService;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;

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
        model.addAttribute("id", id);
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("userInfo", studentService.getPortfolioInfo(id, userDetails));
        model.addAttribute("projects", projectService.getProjectsByStudentId(id));
        return "portfolio";
    }

    @PostMapping("/portfolio")
    @PreAuthorize(value = "hasAuthority('STUDENT')")
    public String confirmCompetence(@RequestParam("result") String result) {
        System.out.println(result);
        // чтоб убрались повторяющиеся (да, как это предотвратить на фронте, не придумала)
        TreeSet<String> tags = new TreeSet<>(Arrays.asList(result.split(" ")));
        return "ok";
    }


}
