package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.itis.practice.models.Group;
import ru.itis.practice.services.config.CommonConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = CommonConfiguration.class)
class GroupServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    @Qualifier("testGroupService")
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
        Group group = entityManager.getEntityManager()
                .find(Group.class, 1L);
        groupService.saveIfNotExist(group);
        int afterSave = getDatabaseSize();
        assertEquals(beforeSave, afterSave);
    }

    @Test
    void testSaveOnNonExistingGroup() {
        Group group = Group.builder()
                .numeric("1234231")
                .build();
        int beforeSave = getDatabaseSize();
        groupService.saveIfNotExist(group);
        int afterSave = getDatabaseSize();
        assertEquals(beforeSave + 1, afterSave);
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
        int after = getDatabaseSize();
        assertEquals(before, after);
    }

    @Test
    void testDeleteOnNonExistingGroup() {
        Group group = Group.builder()
                .numeric("4231315")
                .build();
        int before = getDatabaseSize();
        groupService.delete(group);
        int after = getDatabaseSize();
        assertEquals(before, after);
    }

    @Test
    void testFindByIDShouldReturnEmpty() {
        assertEquals(Optional.empty(), groupService.findById(2005L));
    }

    @Test
    void testFindByIDShouldNotReturnEmpty() {
        assertNotEquals(Optional.empty(), groupService.findById(1L));
    }

    @Test
    void testFindByNumericShouldReturnEmpty() {
        assertEquals(Optional.empty(), groupService.findByNumeric("1234"));
    }

    @Test
    void testFindByNumericShouldNotReturnEmpty() {
        assertNotEquals(Optional.empty(), groupService.findByNumeric("11-802"));
    }
}