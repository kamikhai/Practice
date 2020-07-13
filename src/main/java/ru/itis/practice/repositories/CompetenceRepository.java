package ru.itis.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.practice.models.Competence;

import java.util.List;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {

    List<Competence> findAllByStudent_Id(Long id);
}
