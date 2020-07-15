package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.practice.services.JobProfileService;

@Controller
@AllArgsConstructor
public class JobProfileController {

    private JobProfileService jobProfileService;

    @GetMapping("/job_profile")
    private String getProfile(ModelMap modelMap){
        modelMap.addAttribute("jobs", jobProfileService.getAll());
        return "jobprofile";
    }
}
