package ru.itis.practice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getProject() throws Exception {
        mockMvc.perform(get("/project/12"))
                .andExpect(MockMvcResultMatchers.view().name("project"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("info"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isAccessible"));
    }

    @Test
    void deleteProject() {
        //TODO
    }
}