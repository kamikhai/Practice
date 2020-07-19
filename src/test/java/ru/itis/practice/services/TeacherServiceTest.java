package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.itis.practice.dto.TeacherProfileInfo;
import ru.itis.practice.models.Group;
import ru.itis.practice.models.Teacher;
import ru.itis.practice.models.User;
import ru.itis.practice.services.config.CommonConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = CommonConfiguration.class)
class TeacherServiceTest {

    @Autowired
    @Qualifier("testTeacherService")
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
        assertEquals(25L, teacherService.findByEmail("marsel@gmail.com").getId());
    }

    @Test
    void testProfileInfoByUser() {
        List<Group> groups = new ArrayList<>();
        groups.add(Group.builder()
                .numeric("11-801")
                .id(1L)
                .build());
        groups.add(Group.builder()
                .numeric("11-805")
                .id(5L)
                .build());
        Teacher teacher = Teacher.builder()
                .id(25L)
                .information("2015-2018 высшее образование: Казанский (Приволжский) Федеральный Университет,Квалификация: Магистр2011-2015 высшее образование: Казанский (Приволжский) Федеральный Университет,Квалификация: Бакалавр")
                .position("ассистент, б.с., КФУ / Высшая школа информационных технологий и интеллектуальных систем / Кафедра программной инженерии (основной работник)")
                .link("https://vk.com/marsel.sidikov")
                .user(User.builder()
                        .email("marsel@gmail.com")
                        .id(25L)
                        .fullName("Сидиков Марсель Рафаэлевич")
                        .passHash("$2a$10$75w2gTDCENzcTcgtytWQFuc52WYU26j6EYGXbcgR5JpbdUQ6xgJTe")
                        .photoPath("/img/teacher-1.jpg")
                        .role(User.Role.TEACHER)
                        .build())
                .curatedGroups(groups)
                .build();
        assertEquals(TeacherProfileInfo.from(teacher), teacherService.getProfileInfoByUser(User.builder().email("marsel@gmail.com").build()));
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
        teacherService.updateLink(25L, newLink);
        Teacher teacher = entityManager.getEntityManager()
                .createQuery("from Teacher teacher where teacher.id=:id", Teacher.class)
                .setParameter("id", 25L)
                .getSingleResult();
        assertEquals(newLink, teacher.getLink());
    }

    @Test
    void testUpdatePosition() {
        String newPosition = "newPosition";
        teacherService.updatePosition(25L, newPosition);
        Teacher teacher = entityManager.getEntityManager()
                .createQuery("from Teacher teacher where teacher.id=:id", Teacher.class)
                .setParameter("id", 25L)
                .getSingleResult();
        assertEquals(newPosition, teacher.getPosition());
    }

    @Test
    void testUpdateInformation() {
        String newInfo = "newInfo";
        teacherService.updateInformation(25L, newInfo);
        Teacher teacher = entityManager.getEntityManager()
                .createQuery("from Teacher teacher where teacher.id=:id", Teacher.class)
                .setParameter("id", 25L)
                .getSingleResult();
        assertEquals(newInfo, teacher.getInformation());
    }
}