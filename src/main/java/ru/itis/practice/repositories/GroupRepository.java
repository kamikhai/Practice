package ru.itis.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.practice.models.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByNumeric(String numeric);
    List<Group> findByOrderByNumericDesc();
}
