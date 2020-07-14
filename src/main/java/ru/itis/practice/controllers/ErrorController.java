package ru.itis.practice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//            model.addAttribute("error", statusCode.intValue());
//        }
        return "error";
    }
}
