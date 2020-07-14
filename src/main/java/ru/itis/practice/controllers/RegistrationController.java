package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.practice.models.User;
import ru.itis.practice.services.ExcelService;
import ru.itis.practice.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;
    private ExcelService excelService;

    @GetMapping("/registration")
    public String getRegistration() {
        return "reg";
    }

    @PostMapping("/user_registration")
    public String register(@RequestParam String email, @RequestParam String name,
                           @RequestParam String password, @RequestParam String role) {
        User user = User.builder().email(email).fullName(name).role(User.Role.valueOf(role)).passHash(password).build();
        userService.save(user);
        return "reg";
    }

    @PostMapping("/group_registration")
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletResponse response,
                                                 @RequestParam String group, @RequestParam Long teacher) {
        File answer = excelService.add(file, group, teacher);

        Path f = answer.toPath();
        if (Files.exists(f)){
            response.setHeader("Content-disposition", "attachment;filename=" + f.getFileName());
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            try {
                Files.copy(f, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                System.out.println("Error writing file to output stream. Filename was '{}'" + f.getFileName());
                throw new RuntimeException("IOError writing file to output stream");
            }
        }
        return "reg";
    }
}
