package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.itis.practice.models.User;
import ru.itis.practice.services.config.CommonConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = CommonConfiguration.class)
class UserServiceTest {

    @Autowired
    @Qualifier("testUserService")
    private UserService userService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindByEmailShouldBeEmpty() {
        assertEquals(Optional.empty(), userService.findByEmail("gmail.com"));
    }

    @Test
    void testFindByEmailShouldNotBeEmpty() {
        assertNotEquals(Optional.empty(), userService.findByEmail("123@gmail.com"));
    }

    @Test
    void testIsValidReturnEmpty() {
        User user = User.builder()
                .email("123@gmail.com")
                .passHash("$2a$10$75w2gTDCENzcTcgtytWQFuc52WYU26j6EYGXbcgR5JpbdUQ6xgJTe")
                .build();
        assertEquals(Optional.empty(), userService.isValid(user));
    }

    @Test
    void testIsValidOnCorrectData() {
        User user = User.builder()
                .email("123@gmail.com")
                .passHash("qwerty007")
                .build();
        assertNotEquals(Optional.empty(), userService.isValid(user));
    }

    @Test
    void testSave() {
        int before = size();
        userService.save(User.builder()
                .email("test")
                .fullName("test")
                .passHash("hash")
                .role(User.Role.ADMIN)
                .photoPath("path")
                .build());
        int after = size();
        assertEquals(before + 1, after);
    }

    private int size() {
        return entityManager.getEntityManager()
                .createQuery("from User")
                .getResultList().size();
    }
}