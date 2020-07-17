package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.practice.models.Group;
import ru.itis.practice.repositories.GroupRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupServiceImplTest {

    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void saveIfNotExist() {
        Group expected = groupRepository.findByNumeric("11-802").get();
        Group group = groupService.saveIfNotExist(Group.builder().numeric("11-802").build());
        assertEquals(expected, group);
    }

}