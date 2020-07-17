package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.practice.services.JobProfileService;
import ru.itis.practice.services.StudentService;
import ru.itis.practice.services.TagService;

import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentsController {

    private final StudentService studentService;
    private final TagService tagService;
    private final JobProfileService jobProfileService;

    @GetMapping("/students")
    public String getMain(
            ModelMap map,
            @RequestParam(name = "t", required = false) List<Long> tags,
            @RequestParam(name = "p", required = false) List<Long> profiles
    ) {
        if (tags == null)
            tags = Collections.emptyList();

        if (profiles == null)
            profiles = Collections.emptyList();

        map.put("students", studentService.getAll(tags, profiles));
        map.put("tags", tagService.getAll());
        map.put("profiles", jobProfileService.getAll());

        map.put("selected_tags", tags);
        map.put("selected_prof", profiles);

        return "students";
    }
}
