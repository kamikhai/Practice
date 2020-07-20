package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.itis.practice.dto.PortfolioUserInfo;
import ru.itis.practice.dto.StudentInfoDto;
import ru.itis.practice.dto.StudentProfileInfo;
import ru.itis.practice.models.*;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.config.CommonConfiguration;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = CommonConfiguration.class)
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TestEntityManager entityManager;

    private int size() {
        return entityManager.getEntityManager()
                .createQuery("from Student ")
                .getResultList().size();
    }

    @Test
    void testFindAvailableStudent() {
        assertEquals(3L, studentService.findByEmail("123@gmail.com").getId());
    }

    @Test
    void testFindUnavailableStudent() {
        assertThrows(RuntimeException.class, () -> studentService.findByEmail("test"));
    }

    @Test
    void testProfileInfoByStudent() {
        StudentProfileInfo info = StudentProfileInfo.builder()
                .photoPath("/img/student-4.jpg")
                .fullName("Тестовый студент")
                .jobProfileTitle("Java-разработчик")
                .description("Тестировал, тестирую и буду тестировать! Яблоня от яблони недалеко падает, а проект без тестов - регулярно. Тише едешь - больше тестируешь. Семь тестов на неделе. Сделал дело - пиши тесты. Раз на раз приходится, если писать тесты. Что упало, то не тестировали. Цыплят по Assert.equals() считают. Один в поле - unit test. Семь раз оттесть - один раз запуш. Работа не волк - тесты тоже не волк (вообще мало что волк, кроме волка). На Бога надейся, а сам пиши тесты...")
                .competenceList(Collections.EMPTY_LIST)
                .groupNumeric("11-802")
                .workExperience("Нет опыта")
                .link("https://vk.com/khai_kam")
                .build();
        User user = User.builder()
                .email("123@gmail.com")
                .build();
        assertEquals(info, studentService.getProfileInfoByUser(user));
    }

    @Test
    void testSave() {
        int before = size();
        Group group = entityManager.find(Group.class, 2L);
        User user = User.builder()
                .passHash("hash")
                .email("email")
                .photoPath("path")
                .role(User.Role.STUDENT)
                .fullName("name")
                .build();
        JobProfile jobProfile = entityManager.find(JobProfile.class, 1L);
        Student student = Student.builder()
                .description("des")
                .workExperience("work")
                .link("link")
                .group(group)
                .user(user)
                .jobProfile(jobProfile)
                .build();
        entityManager.persist(user);
        studentService.save(student);
        int after = size();
        assertEquals(before + 1, after);
    }

    @Test
    void testFindByGroupShouldBeEmpty() {
        assertEquals(Collections.EMPTY_LIST, studentService.getAllByGroupId(1L));
    }

    @Test
    void testFindByGroup() {
        Student student = entityManager.getEntityManager()
                .createQuery("from Student student where student.id=:id", Student.class)
                .setParameter("id", 3L)
                .getSingleResult();
        StudentInfoDto info = StudentInfoDto.from(student, Collections.EMPTY_SET);
        List<StudentInfoDto> expected = new ArrayList<>();
        expected.add(info);
        assertEquals(expected, studentService.getAllByGroupId(2L));
    }

    @Test
    void testDescriptionUpdate() {
        String newDesc = "desc";
        studentService.updateDescription(3L, newDesc);
        assertEquals(newDesc, studentService.getById(3L).getDescription());
    }

    @Test
    void testUpdateLink() {
        String newLink = "link";
        studentService.updateLink(3L, newLink);
        assertEquals(newLink, studentService.getById(3L).getLink());
    }

    @Test
    void testUpdateExperience() {
        String newExp = "test";
        studentService.updateExperience(3L, newExp);
        assertEquals(newExp, studentService.getById(3L).getWorkExperience());
    }

    @Test
    void testGetAllNonEmpty() {
        List<Long> tags = new ArrayList<>();
        tags.add(1L);
        assertNotNull(studentService.getAll(tags, Collections.EMPTY_LIST));
    }

    @Test
    void testPortfolioInfoShouldThrowException() {
        assertThrows(RuntimeException.class, () -> studentService.getPortfolioInfo(2005L, null));
    }

    @Test
    void testPortfolioInfo() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .user(User.builder()
                        .id(3L)
                        .build())
                .build();
        PortfolioUserInfo expected = PortfolioUserInfo.from(Student.builder()
                .id(3L)
                .user(User.builder()
                        .photoPath("/img/student-4.jpg")
                        .build())
                .group(Group.builder()
                        .numeric("11-802")
                        .build())
                .build(), true);
        assertEquals(expected, studentService.getPortfolioInfo(3L, userDetails));
    }
}