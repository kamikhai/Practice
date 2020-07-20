package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.models.User;
import ru.itis.practice.SanityCheck;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindByEmailShouldBeEmpty() {
        assertEquals(Optional.empty(), userService.findByEmail("gmail.com"));
    }

    @Test
    void testFindByEmailShouldNotBeEmpty() {
        assertNotEquals(Optional.empty(), userService.findByEmail("test@test.test"));
    }

    @Test
    void testIsValidReturnEmpty() {
        User user = User.builder()
                .email("test@test.test")
                .passHash("incorrect password")
                .build();
        assertEquals(Optional.empty(), userService.isValid(user));
    }

    @Test
    void testIsValidOnCorrectData() {
        User user = User.builder()
                .email("test@test.test")
                .passHash("test")
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