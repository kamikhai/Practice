package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.itis.practice.dto.NewProjectDto;
import ru.itis.practice.dto.ProjectPageInfo;
import ru.itis.practice.models.Project;
import ru.itis.practice.models.User;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.services.config.CommonConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = CommonConfiguration.class)
class ProjectServiceTest {

    @Autowired
    @Qualifier("testProjectService")
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
        UserDetailsImpl userDetails = new UserDetailsImpl(User.builder().id(3L).build());
        assertTrue(projectService.isOwner(12L, userDetails));
    }

    @Test
    void testIsOwnerFromIllegalUser() {
        UserDetailsImpl userDetails = new UserDetailsImpl(User.builder().id(4L).build());
        assertFalse(projectService.isOwner(12L, userDetails));
    }

    @Test
    void testIsOwnerOnNull() {
        assertFalse(projectService.isOwner(3L, null));
    }

    @Test
    void testRemoveFromOwner() {
        assertDoesNotThrow(() -> projectService.remove(12L, 3L, User.builder().id(3L).build()));
    }

    @Test
    void testRemoveFromIllegalAccessor() {
        assertThrows(RuntimeException.class,
                () -> projectService.remove(12L, 3L, User.builder().id(2L).build()));
    }

    @Test
    void testFindWithLinks() {
        String info = "Мужики, заряжай! <br><iframe width=\"100%\" height=\"446\" src=\"https://www.youtube.com/embed/8tnAc88H0Lw\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe><br>\n" +
                "Е5, Спартак, Е5\n" +
                "Ну и ссылки на авторов:\n" +
                "<a href=\"https://github.com/kamikhai/Practice\">https://github.com/kamikhai/Practice</a>\n" +
                "<a href=\"https://github.com/DaniilBogomolov\">https://github.com/DaniilBogomolov</a>";
        assertEquals(info, projectService.getProjectById(12L).getDescription());
    }

    @Test
    void testFindWithImages() {
        String info = "<br><img src=\"https://media.giphy.com/media/l49K0pPIf3YYv5yMg/giphy.gif\"/><br> &quot;aaa&quot;";
        assertEquals(info, projectService.getProjectById(21L).getDescription());
    }

    @Test
    void testFindWithJsInjection() {
        String info = "&lt;script&gt;alert(1);\n" +
                "<br><iframe width=\"100%\" height=\"446\" src=\"https://www.youtube.com/embed/fC_Zi939Ajw\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe><br>&t=4s";
        assertEquals(info, projectService.getProjectById(29L).getDescription());
    }

    @Test
    void testNoProjectFound() {
        assertThrows(RuntimeException.class, () -> projectService.getProjectById(1L));
    }

    @Test
    void testSave() {
        int before = getSize();
        projectService.save(NewProjectDto.builder()
                        .description("Desc")
                        .result("Java")
                        .title("Title")
                        .build(),
                User.builder().email("123@gmail.com").build());
        int after = getSize();
        assertEquals(before + 1, after);
    }

    @Test
    void testGetProjectByStudentSize() {
        int expected = entityManager.getEntityManager()
                .createQuery("from Project project where project.student.user.id=:id")
                .setParameter("id", 3L)
                .getResultList().size();
        assertEquals(expected, projectService.getProjectsByStudentId(3L).size());
    }
}