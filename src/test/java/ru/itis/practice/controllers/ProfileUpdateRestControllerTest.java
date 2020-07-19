package ru.itis.practice.controllers;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.practice.models.User;
import ru.itis.practice.services.TokenService;
import ru.itis.practice.services.UserService;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileUpdateRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProfileUpdateRestController profileUpdateRestController;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    @Autowired
    @Qualifier("tokenServiceImpl")
    private TokenService tokenService;
    private String studentToken;
    private String teacherToken;
    private String teacherToken2;


    @BeforeEach
    void init() {
        User teacher = userService.findByEmail("marsel@gmail.com").get();
        User teacher2 = userService.findByEmail("azatyakupov@gmail.com").get();
        User student = userService.findByEmail("123@gmail.com").get();
        studentToken = tokenService.getToken(student);
        teacherToken = tokenService.getToken(teacher);
        teacherToken2 = tokenService.getToken(teacher2);
    }

    @Test
    void changeStudentAboutMe() throws Exception {
        mockMvc.perform(post("/api/student/about?text=Тестировал, тестирую и буду тестировать! Яблоня от яблони недалеко падает, а прокет без тестов - регулярно. Тише едешь - больше тестируешь. Семь тестов на неделе. Сделал дело - пиши тесты. Раз на раз приходится, если писать тесты. Что упало, то не тестировали. Цыплят по Assert.equals() считают. Один в поле - unit test. Семь раз оттесть - один раз запуш. Работа не волк - тесты тоже не волк (вообще мало что волк, кроме волка). На Бога надейся, а сам пиши тесты.").header("Authorization", studentToken))
                .andExpect(status().isOk());
    }

    @Test
    void changeStudentWorkExperience() throws Exception {
        mockMvc.perform(post("/api/student/experience?text=Работал в маке месяц").header("Authorization", studentToken))
                .andExpect(status().isOk());
    }

    @Test
    void changeStudentLink() throws Exception {
        mockMvc.perform(post("/api/student/link?text=https://vk.com/id0").header("Authorization", studentToken))
                .andExpect(status().isOk());
    }

    @Test
    void confirmCompetence() throws Exception {
        mockMvc.perform(post("/api/student/competence?c=189374").header("Authorization", teacherToken))
                .andExpect(status().isNotFound());
    }

    @Test
    void confirmCompetenceNotFound() throws Exception {
        mockMvc.perform(post("/api/student/competence?c=18").header("Authorization", teacherToken))
                .andExpect(status().isOk());
    }

    @Test
    void confirmCompetenceForbidden() throws Exception {
        mockMvc.perform(post("/api/student/competence?c=18").header("Authorization", teacherToken2))
                .andExpect(status().isForbidden());
    }

    @Test
    void changeTeacherLink() throws Exception {
        mockMvc.perform(post("/api/teacher/link?text=https://vk.com/marsel.sidikov").header("Authorization", teacherToken))
                .andExpect(status().isOk());
    }

    @Test
    void changeTeacherPosition() throws Exception {
        mockMvc.perform(post("/api/teacher/information?text=2015-2018 высшее образование: Казанский (Приволжский) Федеральный Университет,Квалификация: Магистр2011-2015 высшее образование: Казанский (Приволжский) Федеральный Университет,Квалификация: Бакалавр").header("Authorization", teacherToken))
                .andExpect(status().isOk());
    }

    @Test
    void changeTeacherInformation() throws Exception {
        mockMvc.perform(post("/api/teacher/position?text=ассистент, б.с., КФУ / Высшая школа информационных технологий и интеллектуальных систем / Кафедра программной инженерии (основной работник)").header("Authorization", teacherToken))
                .andExpect(status().isOk());
    }
}