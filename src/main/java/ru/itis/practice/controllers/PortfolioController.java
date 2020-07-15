package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.practice.services.TagService;

@Controller
@AllArgsConstructor
public class PortfolioController {

    @GetMapping("/portfolio")
    private String getPortfolio(ModelMap modelMap){
        return "portfolio";
    }
}
