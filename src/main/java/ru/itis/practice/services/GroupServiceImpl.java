package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.practice.dto.GroupInfo;
import ru.itis.practice.models.Group;
import ru.itis.practice.repositories.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    @Override
    public Group saveIfNotExist(Group group) {
        Optional<Group> g = groupRepository.findByNumeric(group.getNumeric());
        if (!g.isPresent()){
            return groupRepository.save(group);
        }
        return g.get();
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

    @Override
    public Optional<Group> findByNumeric(String s) {
        return groupRepository.findByNumeric(s);
    }
}
