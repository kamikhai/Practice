package ru.itis.practice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.models.Competence;
import ru.itis.practice.models.Teacher;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
public class CompetenceServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompetenceService competenceService;

    private Teacher teacher;

    @BeforeEach
    public void init() {
        teacher = entityManager.getEntityManager().find(Teacher.class, 3L);
    }

    @Test
    public void testConfirmOnNonPresentIdShouldThrowException() {
        assertThrows(Throwable.class, () -> competenceService.confirm(999L, teacher));
    }

    @Test
    void testSaveShouldAddNewEntityToDB() {
        int beforeSave = entityManager.getEntityManager()
                .createQuery("from Competence")
                .getResultList()
                .size();

        competenceService.save("test", Collections.emptySet(), 1L);

        int afterSave = entityManager.getEntityManager()
                .createQuery("from Competence")
                .getResultList()
                .size();

        assertEquals(beforeSave + 1, afterSave);
    }

    @Test
    void testConfirmOnPresentId() {
        Competence result = competenceService.confirm(1L, teacher);
        assertEquals(teacher, result.getConfirmedBy());
    }

    @Test
    void testFindByIdShouldReturnEmpty() {
        assertEquals(Optional.empty(), competenceService.findById(999L));
    }

    @Test
    void testFindByIdShouldReturnNonEmpty() {
        assertNotEquals(Optional.empty(), competenceService.findById(1L));
    }
}