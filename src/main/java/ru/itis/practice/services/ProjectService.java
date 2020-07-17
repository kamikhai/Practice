package ru.itis.practice.services;

import ru.itis.practice.dto.NewProjectDto;
import ru.itis.practice.dto.PortfolioProjectInfo;
import ru.itis.practice.dto.ProjectPageInfo;
import ru.itis.practice.models.User;

import java.util.List;

public interface ProjectService {

    List<PortfolioProjectInfo> getProjectsByStudentId(Long id);

    ProjectPageInfo getProjectById(Long id);

    void save(NewProjectDto dto, User user);
}
