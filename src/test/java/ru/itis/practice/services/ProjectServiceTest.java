package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.dto.NewProjectDto;
import ru.itis.practice.models.User;
import ru.itis.practice.security.details.UserDetailsImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TestEntityManager entityManager;

    private int getSize() {
        return entityManager.getEntityManager()
                .createQuery("from Project ")
                .getResultList()
                .size();
    }

    @Test
    void testIsOwnerShouldBeValid() {
        UserDetailsImpl userDetails = new UserDetailsImpl(User.builder().id(1L).build());
        assertTrue(projectService.isOwner(1L, userDetails));
    }

    @Test
    void testIsOwnerFromIllegalUser() {
        UserDetailsImpl userDetails = new UserDetailsImpl(User.builder().id(2L).build());
        assertFalse(projectService.isOwner(1L, userDetails));
    }

    @Test
    void testIsOwnerOnNull() {
        assertFalse(projectService.isOwner(1L, null));
    }

    @Test
    void testRemoveFromOwner() {
        assertDoesNotThrow(() -> projectService.remove(1L, 1L, User.builder().id(1L).build()));
    }

    @Test
    void testRemoveFromIllegalAccessor() {
        assertThrows(RuntimeException.class,
                () -> projectService.remove(1L, 1L, User.builder().id(2L).build()));
    }

    @Test
    void testFindWithYoutube() {
        String description = projectService.getProjectById(1L).getDescription();
        assertTrue(description.contains("<iframe"));
        assertTrue(description.contains("</iframe>"));
        assertTrue(description.contains("src=\"https://www.youtube.com/embed/dQw4w9WgXcQ\""));
    }

    @Test
    void testFindWithLinks() {
        String description = projectService.getProjectById(2L).getDescription();
        assertTrue(description.contains("href=\"https://stackoverflow.com/\""));
    }

    @Test
    void testFindWithImages() {
        String description = projectService.getProjectById(3L).getDescription();
        assertTrue(description.contains("<img"));
        assertTrue(description.contains("src=\"http://www.goatse.cc/img/hello.jpg\""));
    }

    @Test
    void testFindWithJsInjection() {
        String description = projectService.getProjectById(4L).getDescription();
        assertTrue(description.contains("&lt;script&gt;"));
        assertTrue(description.contains("&lt;/script&gt;"));
    }

    @Test
    void testNoProjectFound() {
        assertThrows(RuntimeException.class, () -> projectService.getProjectById(999L));
    }

    @Test
    void testSave() {
        int before = getSize();
        projectService.save(
                NewProjectDto.builder()
                        .description("Desc")
                        .result("Java")
                        .title("Title")
                        .build(),
                User.builder()
                        .email("test@test.test")
                        .build()
        );

        int after = getSize();
        assertEquals(before + 1, after);
    }

    @Test
    void testGetProjectByStudentSize() {
        int expected = entityManager.getEntityManager()
                .createQuery("from Project project where project.student.user.id=:id")
                .setParameter("id", 1L)
                .getResultList().size();

        assertEquals(expected, projectService.getProjectsByStudentId(1L).size());
    }
}