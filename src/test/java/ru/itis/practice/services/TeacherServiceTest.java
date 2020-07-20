package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.dto.TeacherProfileInfo;
import ru.itis.practice.models.Group;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.models.User;
import ru.itis.practice.SanityCheck;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TestEntityManager entityManager;

    private int size() {
        return entityManager.getEntityManager()
                .createQuery("from Teacher ")
                .getResultList().size();
    }

    @Test
    void testFindByEmailShouldThrowException() {
        assertThrows(RuntimeException.class, () -> teacherService.findByEmail("test"));
    }

    @Test
    void testFindByEmailOnCorrectData() {
        assertEquals(3L, teacherService.findByEmail("test@123.test").getId());
    }

    @Test
    void testAllTeachersSize() {
        assertEquals(size(), teacherService.getAllTeachers().size());
    }

    @Test
    void testSave() {
        int before = size();
        User user = User.builder()
                .role(User.Role.TEACHER)
                .email("mail")
                .photoPath("path")
                .passHash("hash")
                .fullName("name")
                .build();
        Teacher teacher = Teacher.builder()
                .user(user)
                .build();
        teacherService.save(teacher);
        int after = size();
        assertEquals(before + 1, after);
    }

    @Test
    void testUpdateLink() {
        String newLink = "newLink";
        teacherService.updateLink(3L, newLink);
        Teacher teacher = entityManager.getEntityManager()
                .createQuery("from Teacher teacher where teacher.id=:id", Teacher.class)
                .setParameter("id", 3L)
                .getSingleResult();
        assertEquals(newLink, teacher.getLink());
    }

    @Test
    void testUpdatePosition() {
        String newPosition = "newPosition";
        teacherService.updatePosition(3L, newPosition);
        Teacher teacher = entityManager.getEntityManager()
                .createQuery("from Teacher teacher where teacher.id=:id", Teacher.class)
                .setParameter("id", 3L)
                .getSingleResult();
        assertEquals(newPosition, teacher.getPosition());
    }

    @Test
    void testUpdateInformation() {
        String newInfo = "newInfo";
        teacherService.updateInformation(3L, newInfo);
        Teacher teacher = entityManager.getEntityManager()
                .createQuery("from Teacher teacher where teacher.id=:id", Teacher.class)
                .setParameter("id", 3L)
                .getSingleResult();
        assertEquals(newInfo, teacher.getInformation());
    }
}