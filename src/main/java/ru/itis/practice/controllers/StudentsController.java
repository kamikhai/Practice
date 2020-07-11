package ru.itis.practice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentsController {

    @GetMapping("/students")
    public String getMain(){
        return "students";
    }
}
