package ru.itis.practice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.practice.models.User;
import ru.itis.practice.services.TokenService;
import ru.itis.practice.services.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminPageRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    private String adminToken;


    @BeforeEach
    void init() {
        User admin = userService.findByEmail("test@test.123").get();
        adminToken = tokenService.getToken(admin);
    }

    @Test
    void addTag() throws Exception {
        mockMvc.perform(post("/api/admin/tags?tag=Rust").with(csrf()).header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTag() throws Exception {
        mockMvc.perform(delete("/api/admin/tags/4").with(csrf()).header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

    @Test
    void addJob() throws Exception {
        mockMvc.perform(post("/api/admin/job?job=Android-разработчик").with(csrf()).header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

    @Test
    void deleteJob() throws Exception {
        mockMvc.perform(delete("/api/admin/job/3").with(csrf()).header("Authorization", adminToken))
                .andExpect(status().isOk());
    }
}