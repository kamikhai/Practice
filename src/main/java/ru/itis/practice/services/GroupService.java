package ru.itis.practice.services;

import ru.itis.practice.dto.GroupInfo;
import ru.itis.practice.models.Group;

import java.util.List;

public interface GroupService {

    Group saveIfNotExist(Group group);
    List<GroupInfo> getAllGroups();
}
