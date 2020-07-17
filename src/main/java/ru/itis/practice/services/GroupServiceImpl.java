package ru.itis.practice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.practice.dto.GroupInfo;
import ru.itis.practice.models.Group;
import ru.itis.practice.repositories.GroupRepository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<GroupInfo> getAllGroups() {
        return GroupInfo.from(groupRepository.findByOrderByNumericDesc());
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public void delete(Group group) {
        groupRepository.delete(group);
    }
}
