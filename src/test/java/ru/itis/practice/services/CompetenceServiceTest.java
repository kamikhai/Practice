package ru.itis.practice.services;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.practice.models.*;
import ru.itis.practice.repositories.CompetenceRepository;
import ru.itis.practice.repositories.StudentRepository;
import ru.itis.practice.repositories.TagRepository;
import ru.itis.practice.services.config.CommonConfiguration;
//import ru.itis.practice.services.config.CommonConfiguration;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = CommonConfiguration.class)
class CompetenceServiceTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CompetenceRepository competenceRepository;

//    @Qualifier("testCompetenceService")
    @Autowired
    private CompetenceService competenceService;

    private Teacher teacher;

    @Before
    void init() {
        teacher = entityManager.getEntityManager().find(Teacher.class, 25L);
    }


    @Test
    void testConfirmOnNonPresentIdShouldThrowException() {
        assertThrows(Throwable.class, () -> competenceService.confirm(2005L, teacher));
    }

    @Test
    void testSaveShouldAddNewEntityToDB() {
        int beforeSave = entityManager.getEntityManager()
                .createQuery("from Competence")
                .getResultList()
                .size();
        String text = "text";
        Set<String> tagNames = new TreeSet<>();
        tagNames.add("Java");
        tagNames.add("Non present tag");
        competenceService.save(text, tagNames, 3L);
        int afterSave = entityManager.getEntityManager()
                .createQuery("from Competence")
                .getResultList()
                .size();
        assertEquals(beforeSave + 1, afterSave);
    }


    @Test
    void testConfirmOnPresentId() {
        Competence result = competenceService.confirm(2L, teacher);
        assertNull(result.getConfirmedBy());
    }

    @Test
    void testFindByIdShouldReturnEmpty() {
        assertEquals(Optional.empty(), competenceService.findById(2005L));
    }

    @Test
    void testFindByIdShouldReturnNonEmpty() {
        assertNotEquals(Optional.empty(), competenceService.findById(3L));
    }


}