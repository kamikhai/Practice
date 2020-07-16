package ru.itis.practice.services;

import ru.itis.practice.dto.PortfolioProjectInfo;

import java.util.List;

public interface ProjectService {

    List<PortfolioProjectInfo> getProjectsByStudentId(Long id);
}
