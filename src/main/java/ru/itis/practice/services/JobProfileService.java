package ru.itis.practice.services;

import ru.itis.practice.models.JobProfile;

import java.util.List;
import java.util.Optional;

public interface JobProfileService {
    List<JobProfile> getAll();

    JobProfile save(JobProfile title);

    Optional<JobProfile> findById(Long id);

    void delete(JobProfile jobProfile);
}
