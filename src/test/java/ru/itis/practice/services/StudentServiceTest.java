package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.dto.PortfolioUserInfo;
import ru.itis.practice.dto.StudentInfoDto;
import ru.itis.practice.dto.StudentProfileInfo;
import ru.itis.practice.models.Group;
import ru.itis.practice.models.JobProfile;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.User;
import ru.itis.practice.security.details.UserDetailsImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
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
        assertEquals(1L, studentService.findByEmail("test@test.test").getId());
    }

    @Test
    void testFindUnavailableStudent() {
        assertThrows(RuntimeException.class, () -> studentService.findByEmail("test"));
    }

    @Test
    void testProfileInfoByStudent() {
        User user = User.builder().email("test@test.test").build();
        StudentProfileInfo info = studentService.getProfileInfoByUser(user);

        assertEquals("/img/empty_user.jpg", info.getPhotoPath());
        assertEquals("я первый", info.getDescription());
        assertEquals("Java-разработчик", info.getJobProfileTitle());
        assertEquals("11-802", info.getGroupNumeric());
        assertEquals(2, info.getCompetenceList().size());
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
        assertEquals(Collections.emptyList(), studentService.getAllByGroupId(3L));
    }

    @Test
    void testFindByGroup() {
        List<StudentInfoDto> list = studentService.getAllByGroupId(1L);
        assertEquals(1, list.size());
        assertEquals(1L, list.get(0).getId());
    }

    @Test
    void testDescriptionUpdate() {
        String newDesc = "desc";
        studentService.updateDescription(1L, newDesc);
        assertEquals(newDesc, studentService.getById(1L).getDescription());
    }

    @Test
    void testUpdateLink() {
        String newLink = "link";
        studentService.updateLink(1L, newLink);
        assertEquals(newLink, studentService.getById(1L).getLink());
    }

    @Test
    void testUpdateExperience() {
        String newExp = "test";
        studentService.updateExperience(1L, newExp);
        assertEquals(newExp, studentService.getById(1L).getWorkExperience());
    }

    @Test
    void testPortfolioInfoShouldThrowException() {
        assertThrows(RuntimeException.class, () -> studentService.getPortfolioInfo(999L, null));
    }

    @Test
    void testPortfolioInfo() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .user(User.builder().id(1L).build())
                .build();

        PortfolioUserInfo expected = PortfolioUserInfo.from(Student.builder()
                .id(1L)
                .user(User.builder().photoPath("/img/empty_user.jpg").build())
                .group(Group.builder().numeric("11-802").build())
                .build(), true);

        assertEquals(expected, studentService.getPortfolioInfo(1L, userDetails));
    }
}