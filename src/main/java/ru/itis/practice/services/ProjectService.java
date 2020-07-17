package ru.itis.practice.services;

import ru.itis.practice.dto.PortfolioProjectInfo;
import ru.itis.practice.dto.ProjectPageInfo;

import java.util.List;

public interface ProjectService {

    List<PortfolioProjectInfo> getProjectsByStudentId(Long id);

    ProjectPageInfo getProjectById(Long id);
}
