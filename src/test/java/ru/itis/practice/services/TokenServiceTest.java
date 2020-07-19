package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.itis.practice.models.User;
import ru.itis.practice.services.config.CommonConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = CommonConfiguration.class)
public class TokenServiceTest {

    @Autowired
    @Qualifier("testTokenService")
    private TokenService tokenService;
    private User user;

    @BeforeEach
    public void init() {
        user = User.builder()
                .id(3L)
                .email("123@gmail.com")
                .fullName("Тестовый студент")
                .role(User.Role.STUDENT)
                .passHash("$2a$10$75w2gTDCENzcTcgtytWQFuc52WYU26j6EYGXbcgR5JpbdUQ6xgJTe")
                .photoPath("/img/student-4.jpg")
                .build();
    }

    @Test
    public void testTokenValidity() {
        assertEquals(tokenService.getToken(user), "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiZW1haWwiOiIxMjNAZ21haWwuY29tIiwibmFtZSI6ItCi0LXRgdGC0L7QstGL0Lkg0YHRgtGD0LTQtdC90YIiLCJyb2xlIjoiU1RVREVOVCJ9.H0a5TvrqwB8UIb3vh4RUcaugcd_81VJIxZa6M0eUj54");
    }
}
