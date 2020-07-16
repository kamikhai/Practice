package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.practice.dto.PortfolioProjectInfo;
import ru.itis.practice.models.Project;
import ru.itis.practice.repositories.ProjectRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private static final String IMAGE_PATH_REGEX = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png|jpeg|svg)";

    @Override
    public List<PortfolioProjectInfo> getProjectsByStudentId(Long id) {
        return projectRepository.findAllByStudent_Id(id)
                .stream()
                .map(this::parseProjectDescription)
                .map(PortfolioProjectInfo::from)
                .collect(Collectors.toList());
    }


    private Project parseProjectDescription(Project project) {
        project.setDescription(addImagesTags(project.getDescription()));
        //TODO: parse youtube player
        return project;
    }

    private String addImagesTags(String string) {
        Matcher matcher = Pattern.compile(IMAGE_PATH_REGEX).matcher(string);
        if (matcher.find())
            return matcher.replaceAll("<img src=\"" + matcher.group() + "\"/>");
        else
            return string;
    }
}
