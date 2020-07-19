package ru.itis.practice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCustomStudentProfile() throws Exception {
        mockMvc.perform(get("/profile/3"))
                .andExpect(MockMvcResultMatchers.view().name("profile"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isOwnProfile"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tags"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("groups"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("token"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("profileInfo"));
    }

    @Test
    void getCustomTeacherProfile() throws Exception {
        mockMvc.perform(get("/profile/25"))
                .andExpect(MockMvcResultMatchers.view().name("teacher"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isOwnProfile"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tags"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("groups"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("token"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isAdmin"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("profileInfo"));
    }

    @Test
    void getCustomAdminProfile() throws Exception {
        mockMvc.perform(get("/profile/4"))
                .andExpect(MockMvcResultMatchers.view().name("error"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isOwnProfile"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tags"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("groups"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("token"));
    }

    @Test
    void getSelf() {
        //TODO
    }

    @Test
    void addCompetence() {
        //TODO
    }

    @Test
    void uploadFile() {
        //TODO
    }

    @Test
    void read() throws Exception {
        mockMvc.perform(get("/profile/photo/5z4oVd9sQCjMZCRNj5JgS8lOruujrB3mEyvvHgX5.jpg"))
                .andExpect(status().isOk());
    }
}