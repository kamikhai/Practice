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
class PortfolioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPortfolio() throws Exception {
        mockMvc.perform(get("/portfolio/3"))
                .andExpect(MockMvcResultMatchers.view().name("portfolio"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("projects"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userInfo"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tags"));
    }

    @Test
    void confirmCompetence() {
        //TODO: опять не сделать из-за SECURITY
    }
}