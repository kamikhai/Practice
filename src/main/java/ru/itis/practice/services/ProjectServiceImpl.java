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
    public static final String YOUTUBE_PATH_REGEX = "((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?.*$";


    @Override
    public List<PortfolioProjectInfo> getProjectsByStudentId(Long id) {
        return projectRepository.findAllByStudent_Id(id)
                .stream()
                .map(this::parseProjectDescription)
                .map(PortfolioProjectInfo::from)
                .collect(Collectors.toList());
    }


    private Project parseProjectDescription(Project project) {
        project.setDescription(addYoutubePlayer(project.getDescription(), ""));
        project.setDescription(addImagesTags(project.getDescription()));
        return project;
    }

    private String addImagesTags(String string) {
        Matcher matcher = Pattern.compile(IMAGE_PATH_REGEX).matcher(string);
        if (matcher.find())
            return matcher.replaceAll("<img src=\"" + matcher.group() + "\"/>");
        else
            return string;
    }

    private String addYoutubePlayer(String string, String result) {
        Matcher matcher = Pattern.compile(YOUTUBE_PATH_REGEX).matcher(string);
        if (matcher.find()) {
            String nextPart = string.substring(string.indexOf(matcher.group(5)) + matcher.group(5).length());
            String replacement = matcher.replaceAll(generateIFrame(matcher.group(5)));
            return addYoutubePlayer(nextPart, result.concat(replacement));
        } else {
            if (string.equals("")) return result;
            else return result.concat(string);
        }
    }

    private String generateIFrame(String id) {
        return "<br><iframe width=\"100%\" height=\"446\" src=\"https://www.youtube.com/embed/" + id + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe><br>";
    }
}
