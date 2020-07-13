package ru.itis.practice.services;

import ru.itis.practice.models.Group;

public interface GroupService {
    Group saveIfNotExist(Group group);
}
