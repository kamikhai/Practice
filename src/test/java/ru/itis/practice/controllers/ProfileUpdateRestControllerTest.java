package ru.itis.practice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.practice.models.User;
import ru.itis.practice.services.TokenService;
import ru.itis.practice.services.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileUpdateRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    @Autowired
    @Qualifier("tokenServiceImpl")
    private TokenService tokenService;
    private String studentToken;
    private String teacherToken;


    @BeforeEach
    void init() {
        User teacher = userService.findByEmail("test@123.test").get();
        User student = userService.findByEmail("123@test.test").get();
        studentToken = tokenService.getToken(student);
        teacherToken = tokenService.getToken(teacher);
    }

    @Test
    void changeStudentAboutMe() throws Exception {
        mockMvc.perform(post("/api/student/about?text=-").with(csrf()).header("Authorization", studentToken))
                .andExpect(status().isOk());
    }

    @Test
    void changeStudentWorkExperience() throws Exception {
        mockMvc.perform(post("/api/student/experience?text=-").with(csrf()).header("Authorization", studentToken))
                .andExpect(status().isOk());
    }

    @Test
    void changeStudentLink() throws Exception {
        mockMvc.perform(post("/api/student/link?text=https://vk.com/id0").with(csrf()).header("Authorization", studentToken))
                .andExpect(status().isOk());
    }

    @Test
    void confirmCompetence() throws Exception {
        mockMvc.perform(post("/api/student/competence?c=2").with(csrf()).header("Authorization", teacherToken))
                .andExpect(status().isOk());
    }

    @Test
    void confirmCompetenceNotFound() throws Exception {
        mockMvc.perform(post("/api/student/competence?c=999").with(csrf()).header("Authorization", teacherToken))
                .andExpect(status().isNotFound());
    }

    @Test
    void changeTeacherLink() throws Exception {
        mockMvc.perform(post("/api/teacher/link?text=https://vk.com/id0").with(csrf()).header("Authorization", teacherToken))
                .andExpect(status().isOk());
    }

    @Test
    void changeTeacherPosition() throws Exception {
        mockMvc.perform(post("/api/teacher/information?text=-").with(csrf()).header("Authorization", teacherToken))
                .andExpect(status().isOk());
    }

    @Test
    void changeTeacherInformation() throws Exception {
        mockMvc.perform(post("/api/teacher/position?text=").with(csrf()).header("Authorization", teacherToken))
                .andExpect(status().isOk());
    }
}