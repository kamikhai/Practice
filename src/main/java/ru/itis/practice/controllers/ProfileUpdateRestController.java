package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.CompetenceService;
import ru.itis.practice.services.StudentService;
import ru.itis.practice.services.TeacherService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProfileUpdateRestController {
    private StudentService studentService;
    private CompetenceService competenceService;
    private TeacherService teacherService;


    @PostMapping("/student/about")
    @PreAuthorize(value = "hasAuthority('STUDENT')")
    public ResponseEntity<String> changeAboutMe(@RequestParam("text") String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        studentService.updateDescription(((UserDetailsImpl) authentication.getDetails()).getUser().getId(), text);
        return ResponseEntity.ok().body("Информация успешно обновлена");
    }

    @PostMapping("/student/competence")
    @PreAuthorize(value = "hasAuthority('TEACHER')")
    public ResponseEntity<String> confirmCompetence(@RequestParam("student") Long student) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        competenceService.confirm(student, teacherService.findByEmail(((UserDetailsImpl) authentication.getDetails()).getUser().getEmail()));
        return ResponseEntity.ok().body("Компетенция успешно подтверждена");
    }
}