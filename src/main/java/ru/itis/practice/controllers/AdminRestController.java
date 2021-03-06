package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.practice.dto.IdDto;
import ru.itis.practice.models.Group;
import ru.itis.practice.models.JobProfile;
import ru.itis.practice.models.Tag;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.services.GroupService;
import ru.itis.practice.services.JobProfileService;
import ru.itis.practice.services.TagService;
import ru.itis.practice.services.TeacherService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class AdminRestController {
    private GroupService groupService;
    private TagService tagService;
    private JobProfileService jobProfileService;
    private TeacherService teacherService;

    @DeleteMapping("/group/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id) {
        Group group = groupService.findById(id).get();
        groupService.delete(group);
        return ResponseEntity.ok("Группа успешно удалена");
    }

    @PostMapping("/tags")
    public ResponseEntity<String> addTag(@RequestParam String tag) {
        tagService.save(Tag.builder().name(tag).build());
        return ResponseEntity.ok().body("Тег успешно добавлен");
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        Tag tag = tagService.findById(id).get();
        tagService.delete(tag);
        return ResponseEntity.ok("Тег успешно удален");
    }


    @PostMapping("/job")
    public ResponseEntity<String> addJob(@RequestParam String job) {
        jobProfileService.save(JobProfile.builder().title(job).build());
        return ResponseEntity.ok().body("Профиль работы успешно добавлен");
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        JobProfile jobProfile = jobProfileService.findById(id).get();
        jobProfileService.delete(jobProfile);
        return ResponseEntity.ok("Профиль работы успешно удален");
    }

    @PostMapping("/teacher/{id}")
    public ResponseEntity<String> addGroup(@PathVariable Long id, @RequestBody IdDto group) {
        Teacher teacher = teacherService.findById(id).get();
        Group g = groupService.findById(group.getId()).get();
        if (!teacher.getCuratedGroups().contains(g)){
            teacher.getCuratedGroups().add(g);
            teacherService.save(teacher);
        }
        return ResponseEntity.ok("Группо успешно добавлена");
    }
}
