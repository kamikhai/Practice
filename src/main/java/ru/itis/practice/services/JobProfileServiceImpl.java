package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.practice.models.JobProfile;
import ru.itis.practice.repositories.JobProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobProfileServiceImpl implements JobProfileService {

    private final JobProfileRepository profileRepository;

    @Override
    public List<JobProfile> getAll() {
        return profileRepository.findAllByOrderByTitle();
    }

    @Override
    public JobProfile save(JobProfile jobProfile) {
        return profileRepository.save(jobProfile);
    }

    @Override
    public Optional<JobProfile> findById(Long id) {
        return profileRepository.findById(id);
    }

    @Override
    public void delete(JobProfile jobProfile) {
        profileRepository.delete(jobProfile);
    }
}
