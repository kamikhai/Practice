package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.practice.models.JobProfile;
import ru.itis.practice.repositories.JobProfileRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class JobProfileServiceImpl implements JobProfileService {

    private final JobProfileRepository profileRepository;

    @Override
    public List<JobProfile> getAll() {
        return profileRepository.findAllByOrderByTitle();
    }
}
