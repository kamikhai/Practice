package ru.itis.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.practice.models.JobProfile;

import java.util.List;

public interface JobProfileRepository extends JpaRepository<JobProfile, Long> {
    List<JobProfile> findAllByOrderByTitle();
}
