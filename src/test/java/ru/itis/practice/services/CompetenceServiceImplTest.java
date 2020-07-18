package ru.itis.practice.services;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.repositories.CompetenceRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompetenceServiceImplTest {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CompetenceService competenceService;
    private Teacher teacher;
    private Competence expected;

    @BeforeEach
    public void getTeacher(){
       teacher = teacherService.findByEmail("marsel@gmail.com");
       expected = competenceService.findById(1L).get();
    }

    @Test
    void confirm() {
        expected.setConfirmedBy(teacher);
        Competence competence = competenceService.confirm(1L, teacher);
        assertEquals(expected.getConfirmedBy().getUser().getId(), competence.getConfirmedBy().getUser().getId());
    }

}