package ru.itis.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import ru.itis.practice.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestConfiguration {

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testProperties() {
        assertEquals("jdbc:h2:mem:test", env.getProperty("spring.datasource.url"));
    }

    @Test
    public void testData() {
        assertEquals(4, userRepository.findAll().size());
    }
}
