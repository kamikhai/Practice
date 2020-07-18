package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.JobProfileService;
import ru.itis.practice.services.TokenService;

@Controller
@AllArgsConstructor
public class JobProfileController {

    private JobProfileService jobProfileService;
    private TokenService tokenService;

    @GetMapping("/job_profile")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getProfile(ModelMap modelMap, @AuthenticationPrincipal UserDetailsImpl userDetails){
        modelMap.put("token", tokenService.getToken(userDetails.getUser()));
        modelMap.addAttribute("jobs", jobProfileService.getAll());
        return "jobprofile";
    }
}
