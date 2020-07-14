package ru.itis.practice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeachersController {

    @GetMapping("/teachers")
    private String getTeachers(){
        return "teachers";
    }
}
