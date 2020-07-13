package ru.itis.practice.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String getMain(Authentication authentication){
        return authentication == null ? "redirect:/login" : "profile";
    }
}
