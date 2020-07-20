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
class TeacherGroupsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGroups() throws Exception {
        mockMvc.perform(get("/my_students/25"))
                .andExpect(MockMvcResultMatchers.view().name("teachergroups"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("groups"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("photo"));
    }

    @Test
    void getStudents() throws Exception {
        mockMvc.perform(get("/students_group?g=2"))
                .andExpect(MockMvcResultMatchers.view().name("id"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("id"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("admin"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("token"));
    }

}