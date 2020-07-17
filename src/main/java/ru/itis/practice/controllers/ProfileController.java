package ru.itis.practice.controllers;


import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.User;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.*;

import java.util.Arrays;
import java.util.TreeSet;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private StudentService studentService;
    private UserService userService;
    private TeacherService teacherService;
    private GroupService groupService;
    private CompetenceService competenceService;
    private TagService tagService;
    private TokenService tokenService;
    private ImageService imageService;


    @GetMapping("/{id}")
    public String getCustomProfile(@PathVariable Long id, Model model,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userService.getUserById(id);
        model.addAttribute("isOwnProfile", false);
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("groups", groupService.getAllGroups());
        if (userDetails != null) {
            model.addAttribute("token", tokenService.getToken(userDetails.getUser()));
        } else {
            model.addAttribute("token", "");
        }
        if (user.getRole().equals(User.Role.STUDENT)) {
            if (userDetails != null) {
                model.addAttribute("user", userDetails.getUser().getRole().name());
            } else {
                model.addAttribute("user", "ANONYMOUS");
            }
            model.addAttribute("profileInfo", studentService.getProfileInfoByUser(user));
            return "profile";
        } else if (user.getRole().equals(User.Role.TEACHER)) {
            if (userDetails != null) {
                model.addAttribute("isAdmin", userDetails.getUser().getRole().equals(User.Role.ADMIN) ? true : false);
            } else {
                model.addAttribute("isAdmin", false);
            }
            model.addAttribute("profileInfo", teacherService.getProfileInfoByUser(user));
            return "teacher";
        } else {
            return "error";
        }
    }

    @GetMapping
    @PreAuthorize(value = "isAuthenticated()")
    public String getSelf(@AuthenticationPrincipal UserDetailsImpl userDetails,
                          Model model) {
        model.addAttribute("id", userDetails.getUser().getId());
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("token", tokenService.getToken(userDetails.getUser()));
        model.addAttribute("isOwnProfile", true);
        model.addAttribute("groups", groupService.getAllGroups());
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("STUDENT"))) {
            model.addAttribute("profileInfo", studentService.getProfileInfoByUser(userDetails.getUser()));
            return "profile";
        } else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("TEACHER"))) {
            model.addAttribute("isAdmin", false);
            model.addAttribute("profileInfo", teacherService.getProfileInfoByUser(userDetails.getUser()));
            return "teacher";
        } else {
            model.addAttribute("groups", groupService.getAllGroups());
            return "admin";
        }
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('STUDENT')")
    public String confirmCompetence(@RequestParam("result") String result) {
        System.out.println(result);
        // чтоб убрались повторяющиеся (да, как это предотвратить на фронте, не придумала)
        TreeSet<String> tags = new TreeSet<>(Arrays.asList(result.split(" ")));
        return "ok";
    }

    @PostMapping("/photo")
    @PreAuthorize(value = "isAuthenticated()")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(123);
        imageService.save(multipartFile, userDetails.getUserId());
        return ResponseEntity.ok().body("Ваше фото успешно загружено");
    }


    @PreAuthorize("permitAll()")
    @GetMapping("/photo/{file-name:.+}")
    public ResponseEntity<Resource> read(@PathVariable("file-name") String fileName) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageService.get(fileName));
    }
}
