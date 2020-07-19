package ru.itis.practice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.practice.models.User;
import ru.itis.practice.services.TokenService;
import ru.itis.practice.services.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminPageRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AdminRestController adminRestController;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    private String adminToken;


    @BeforeEach
    void init() {
        User admin = userService.findByEmail("kamilla.hairul@gmail.com").get();
        adminToken = tokenService.getToken(admin);
    }



    @Test
    void deleteGroup() throws Exception {
        mockMvc.perform(delete("/api/admin/group/40").header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

    @Test
    void addTag() throws Exception {
        mockMvc.perform(post("/api/admin/tags?tag=Pascal").header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTag() throws Exception {
        mockMvc.perform(delete("/api/admin/tags/54").header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

    @Test
    void addJob() throws Exception {
        mockMvc.perform(post("/api/admin/job?job=Android-разработчик").header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

    @Test
    void deleteJob() throws Exception {
        mockMvc.perform(delete("/api/admin/job/27").header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

    @Test
    void addGroup() throws Exception {
        mockMvc.perform(post("/api/admin/teacher/25").contentType(MediaType.APPLICATION_JSON).content("{\"group\":12}").header("Authorization", adminToken))
                .andExpect(status().isOk());
    }

}