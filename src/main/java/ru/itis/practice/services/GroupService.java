package ru.itis.practice.services;

import ru.itis.practice.dto.GroupInfo;
import ru.itis.practice.models.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Group saveIfNotExist(Group group);
    List<GroupInfo> getAllGroups();
    Optional<Group> findById(Long id);
    void delete(Group group);

    Optional<Group> findByNumeric(String s);
}
