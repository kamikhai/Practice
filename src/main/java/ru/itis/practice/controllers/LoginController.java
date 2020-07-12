package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.practice.services.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

	private UserService userService;

	@GetMapping
	public String getSignInPage(Authentication authentication) {
		return authentication == null ? "login" : "redirect:/profile";
	}
}
