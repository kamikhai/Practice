package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.practice.services.JobProfileService;
import ru.itis.practice.services.TagService;

@Controller
@AllArgsConstructor
public class TagsController {

    private TagService tagService;

    @GetMapping("/tags")
    private String getProfile(ModelMap modelMap){
        modelMap.addAttribute("tags", tagService.getAll());
        return "tags";
    }
}
