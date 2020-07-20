package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.models.Group;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
class GroupServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GroupService groupService;

    private int getDatabaseSize() {
        return entityManager.getEntityManager()
                .createQuery("from Group")
                .getResultList()
                .size();
    }

    @Test
    void testSaveOnExistingGroup() {
        int beforeSave = getDatabaseSize();
        Group group = entityManager.getEntityManager().find(Group.class, 1L);

        groupService.saveIfNotExist(group);
        assertEquals(beforeSave, getDatabaseSize());
    }

    @Test
    void testSaveOnNonExistingGroup() {
        Group group = Group.builder()
                .numeric("1234231")
                .build();

        int beforeSave = getDatabaseSize();
        groupService.saveIfNotExist(group);

        assertEquals(beforeSave + 1, getDatabaseSize());
    }

    @Test
    void testGetAllGroupsSize() {
        assertEquals(getDatabaseSize(), groupService.getAllGroups().size());
    }

    @Test
    void testDeleteOnExistingGroup() {
        int before = getDatabaseSize();
        Group newGroup = Group.builder()
                .numeric("21433241")
                .build();

        newGroup.setId(entityManager.persistAndGetId(newGroup, Long.class));
        groupService.delete(newGroup);

        assertEquals(before, getDatabaseSize());
    }

    @Test
    void testDeleteOnNonExistingGroup() {
        Group group = Group.builder()
                .numeric("4231315")
                .build();

        int before = getDatabaseSize();
        groupService.delete(group);

        assertEquals(before, getDatabaseSize());
    }

    @Test
    void testFindByIDShouldReturnEmpty() {
        assertEquals(Optional.empty(), groupService.findById(999L));
    }

    @Test
    void testFindByIDShouldNotReturnEmpty() {
        assertNotEquals(Optional.empty(), groupService.findById(1L));
    }

    @Test
    void testFindByNumericShouldReturnEmpty() {
        assertEquals(Optional.empty(), groupService.findByNumeric("test"));
    }

    @Test
    void testFindByNumericShouldNotReturnEmpty() {
        assertNotEquals(Optional.empty(), groupService.findByNumeric("11-802"));
    }
}