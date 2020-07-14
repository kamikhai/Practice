package ru.itis.practice.controllers;



import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.practice.models.User;
import ru.itis.practice.repositories.StudentRepository;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.StudentService;
import ru.itis.practice.services.TeacherService;
import ru.itis.practice.services.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private StudentService studentService;
    private UserService userService;
    private TeacherService teacherService;

    @GetMapping("/{id}")
    public String getCustomProfile(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user.getRole().equals(User.Role.STUDENT)) {
            model.addAttribute("profileInfo", studentService.getProfileInfoByUser(user));
            return "profile";
        } else if (user.getRole().equals(User.Role.TEACHER)) {
            model.addAttribute("profileInfo", teacherService.getProfileInfoByUser(user));
            return "teacher";
        } else {
            //TODO: admin profile
            return null;
        }
    }

    @GetMapping
    @PreAuthorize(value = "isAuthenticated()")
    public String getSelf(@AuthenticationPrincipal UserDetailsImpl userDetails,
                          Model model) {
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("STUDENT"))){
            model.addAttribute("profileInfo", studentService.getProfileInfoByUser(userDetails.getUser()));
            return "profile";
        } else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("TEACHER"))){
            return "teacher";
        }else {
            return "profile";
         }
    }
}
