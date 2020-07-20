package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.itis.practice.models.JobProfile;
import ru.itis.practice.TestConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfiguration.class)
class JobProfileServiceTest {

    @Autowired
    private JobProfileService jobProfileService;

    @Autowired
    private TestEntityManager entityManager;

    private int getDatabaseSize() {
        return entityManager.getEntityManager()
                .createQuery("from JobProfile ")
                .getResultList()
                .size();
    }

    @Test
    void testGetAll() {
        assertEquals(getDatabaseSize(), jobProfileService.getAll().size());
    }

    @Test
    void testSave() {
        int before = getDatabaseSize();
        JobProfile jobProfile = JobProfile.builder()
                .title("testProfile")
                .build();
        jobProfileService.save(jobProfile);
        int after = getDatabaseSize();
        assertEquals(before + 1, after);
    }

    @Test
    void testFindByIdShouldReturnEmpty() {
        assertEquals(Optional.empty(), jobProfileService.findById(2005L));
    }

    @Test
    void testFindByIdShouldNotReturnEmpty() {
        assertNotEquals(Optional.empty(), jobProfileService.findById(1L));
    }

    @Test
    void testDeleteOnExistingEntity() {
        int before = getDatabaseSize();
        JobProfile jobProfile = JobProfile.builder()
                .title("Java")
                .build();
        jobProfile.setId(entityManager.persistAndGetId(jobProfile, Long.class));
        jobProfileService.delete(jobProfile);
        int after = getDatabaseSize();
        assertEquals(before, after);
    }

    @Test
    void testDeleteOnNonExistingEntity() {
        JobProfile jobProfile = JobProfile.builder()
                .title("1111")
                .build();
        int before = getDatabaseSize();
        jobProfileService.delete(jobProfile);
        int after = getDatabaseSize();
        assertEquals(before, after);
    }
}