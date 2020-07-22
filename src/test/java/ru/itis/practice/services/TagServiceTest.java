package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.models.Tag;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Autowired
    private TestEntityManager entityManager;

    private int getDatabaseSize() {
        return entityManager.getEntityManager()
                .createQuery("from Tag ")
                .getResultList()
                .size();
    }

    @Test
    void testGetAll() {
        assertEquals(getDatabaseSize(), tagService.getAll().size());
    }

    @Test
    void testGetByNonExistingName() {
        assertNull(tagService.getByName("no tag present!"));
    }

    @Test
    void testGetByExistingName() {
        assertNotNull(tagService.getByName("Java"));
    }

    @Test
    void testSave() {
        int before = getDatabaseSize();
        Tag tag = Tag.builder().name("test tag").build();
        tagService.save(tag);
        int after = getDatabaseSize();
        assertEquals(before + 1, after);
    }

    @Test
    void testFindByIdShouldBeEmpty() {
        assertEquals(Optional.empty(), tagService.findById(999L));
    }

    @Test
    void testFindByIdShouldNotBeEmpty() {
        assertNotEquals(Optional.empty(), tagService.findById(1L));
    }

    @Test
    void testDelete() {
        int before = getDatabaseSize();
        Tag tag = Tag.builder().name("test Tag").build();
        tag.setId(entityManager.persistAndGetId(tag, Long.class));
        tagService.delete(tag);
        int after = getDatabaseSize();
        assertEquals(before, after);
    }
}