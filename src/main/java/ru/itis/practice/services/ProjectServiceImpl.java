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
    public static final String IMAGE_PATH_REGEX = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png|jpeg|svg)";
    public static final String YOUTUBE_PATH_REGEX = "((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?.*$";
    public static final String COMMON_URL_REGEX = "(http|ftp|https):\\/\\/([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:\\/~+#-]*[\\w@?^=%&\\/~+#-])?";
    public static final Pattern IMAGE_PATTERN = Pattern.compile(IMAGE_PATH_REGEX);
    public static final Pattern YOUTUBE_PATTERN = Pattern.compile(YOUTUBE_PATH_REGEX);

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
        project.setDescription(addOtherLinks(project.getDescription()));
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

    private String addOtherLinks(String string) {
        Matcher matcher = Pattern.compile(COMMON_URL_REGEX).matcher(string);
        StringBuilder result = new StringBuilder();
        int lastAppendedIndex = 0;
        while (matcher.find()) {
            String next = matcher.group();
            int first = matcher.start();
            int last = matcher.end();
            result.append(string, lastAppendedIndex, first);
            if (IMAGE_PATTERN.matcher(next).matches() || YOUTUBE_PATTERN.matcher(next).matches())
                result.append(next);
            else
                result.append("<a href=\"").append(next).append("\">").append(next).append("</a>");
            lastAppendedIndex = last;
        }
        if (lastAppendedIndex != string.length()) result.append(string.substring(lastAppendedIndex));
        return result.toString();
    }

    private String generateIFrame(String id) {
        return "<br><iframe width=\"100%\" height=\"446\" src=\"https://www.youtube.com/embed/" + id + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe><br>";
    }
}
