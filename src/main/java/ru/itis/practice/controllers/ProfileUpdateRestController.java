package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.practice.dto.IdDto;
import ru.itis.practice.models.*;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.CompetenceService;
import ru.itis.practice.services.JobProfileService;
import ru.itis.practice.services.StudentService;
import ru.itis.practice.services.TeacherService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProfileUpdateRestController {

    private StudentService studentService;
    private CompetenceService competenceService;
    private TeacherService teacherService;
    private JobProfileService jobProfileService;


    @PostMapping("/student/about")
    @PreAuthorize(value = "hasAuthority('STUDENT')")
    public ResponseEntity<String> changeStudentAboutMe(@RequestParam("text") String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        studentService.updateDescription(((UserDetailsImpl) authentication.getDetails()).getUserId(), text);
        return ResponseEntity.ok().body("Информация успешно обновлена");
    }

    @PostMapping("/student/experience")
    @PreAuthorize(value = "hasAuthority('STUDENT')")
    public ResponseEntity<String> changeStudentWorkExperience(@RequestParam("text") String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        studentService.updateExperience(((UserDetailsImpl) authentication.getDetails()).getUserId(), text);
        return ResponseEntity.ok("Информация успешно обновлена");
    }

    @PostMapping("/student/link")
    @PreAuthorize(value = "hasAuthority('STUDENT')")
    public ResponseEntity<String> changeStudentLink(@RequestParam("text") String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        studentService.updateLink(((UserDetailsImpl) authentication.getDetails()).getUserId(), text);
        return ResponseEntity.ok("Информация успешно обновлена");
    }


    @PostMapping("/student/competence")
    @PreAuthorize(value = "hasAuthority('TEACHER')")
    public ResponseEntity<String> confirmCompetence(@RequestParam("c") Long comp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = teacherService.findByEmail(((UserDetailsImpl) authentication.getDetails()).getUser().getEmail());
        Optional<Competence> competenceCandidate = competenceService.findById(comp);
        if (competenceCandidate.isPresent()){
            Competence competence = competenceCandidate.get();
            if (teacher.getCuratedGroups().contains(competence.getStudent().getGroup())){
                competenceService.confirm(comp,
                        teacherService.findByEmail(((UserDetailsImpl) authentication.getDetails()).getUser().getEmail()));
                return ResponseEntity.ok("Компетенция успешно подтверждена");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Вы не являетесь ответственым за этого студента");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Компетенция не найдена");
    }

    @PostMapping("/teacher/link")
    @PreAuthorize(value = "hasAuthority('TEACHER')")
    public ResponseEntity<String> changeTeacherLink(@RequestParam("text") String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        teacherService.updateLink(((UserDetailsImpl) authentication.getDetails()).getUserId(), text);
        return ResponseEntity.ok("Информация успешно обновлена");
    }

    @PostMapping("/teacher/position")
    @PreAuthorize(value = "hasAuthority('TEACHER')")
    public ResponseEntity<String> changeTeacherPosition(@RequestParam("text") String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        teacherService.updatePosition(((UserDetailsImpl) authentication.getDetails()).getUserId(), text);
        return ResponseEntity.ok("Информация успешно обновлена");
    }

    @PostMapping("/teacher/information")
    @PreAuthorize(value = "hasAuthority('TEACHER')")
    public ResponseEntity<String> changeTeacherInformation(@RequestParam("text") String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        teacherService.updateInformation(((UserDetailsImpl) authentication.getDetails()).getUserId(), text);
        return ResponseEntity.ok("Информация успешно обновлена");
    }

    @PostMapping("/student/jobprofile")
    @PreAuthorize(value = "hasAuthority('STUDENT')")
    public ResponseEntity<String> addGroup(@RequestBody IdDto dto) {
        JobProfile jobProfile = jobProfileService.findById(dto.getId()).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        Student student = studentService.findByEmail(userDetails.getUser().getEmail());
        student.setJobProfile(jobProfile);
        studentService.save(student);
        return ResponseEntity.ok("Информация успешно обновлена");
    }
}