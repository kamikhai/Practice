package ru.itis.practice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.practice.models.Group;
import ru.itis.practice.repositories.GroupRepository;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group saveIfNotExist(Group group) {
        Group g = groupRepository.findByNumeric(group.getNumeric());
        if (g == null){
            return groupRepository.save(group);
        }
        return g;
    }
}
