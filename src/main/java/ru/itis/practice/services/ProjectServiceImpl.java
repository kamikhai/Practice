package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.practice.dto.NewProjectDto;
import ru.itis.practice.dto.PortfolioProjectInfo;
import ru.itis.practice.dto.ProjectPageInfo;
import ru.itis.practice.models.Project;
import ru.itis.practice.models.User;
import ru.itis.practice.repositories.ProjectRepository;
import ru.itis.practice.repositories.StudentRepository;
import ru.itis.practice.repositories.TagRepository;
import ru.itis.practice.security.details.UserDetailsImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private StudentRepository studentRepository;
    private TagRepository tagRepository;
    public static final String IMAGE_PATH_REGEX = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png|jpeg|svg)";
    public static final String YOUTUBE_PATH_REGEX = "((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?.*$";
    public static final String COMMON_URL_REGEX = "(http|ftp|https):\\/\\/([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:\\/~+#-]*[\\w@?^=%&\\/~+#-])?";
    public static final Pattern IMAGE_PATTERN = Pattern.compile(IMAGE_PATH_REGEX);
    public static final Pattern YOUTUBE_PATTERN = Pattern.compile(YOUTUBE_PATH_REGEX, Pattern.DOTALL);

    @Override
    public List<PortfolioProjectInfo> getProjectsByStudentId(Long id) {
        return projectRepository.findAllByStudent_Id(id)
                .stream()
                .map(this::parseProjectDescription)
                .map(PortfolioProjectInfo::from)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectPageInfo getProjectById(Long id) {
        Optional<Project> projectCandidate = projectRepository.findById(id);
        if  (projectCandidate.isPresent()) {
            Project project = parseProjectDescription(projectCandidate.get());
            return ProjectPageInfo.from(project);
        }
        throw new RuntimeException("No project found!");
    }

    @Override
    @Transactional
    public void save(NewProjectDto dto, User user) {
        Project project = Project.builder()
                .description(dto.getDescription())
                .title(dto.getTitle())
                .tags(Arrays.stream(dto.getResult().split(" ")).distinct().map(tagRepository::findByName).collect(Collectors.toList()))
                .student(studentRepository.findStudentByUser_Email(user.getEmail()).orElseThrow(RuntimeException::new))
                .build();
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void remove(Long projectId, Long ownerId, User accessor) {
        if (ownerId.equals(accessor.getId()))
            projectRepository.deleteById(projectId);
        else
            throw new RuntimeException("Illegal access");
    }

    @Override
    public boolean isOwner(Long projectId, UserDetailsImpl possibleOwner) {
        if (possibleOwner != null) {
            Optional<Project> projectCandidate = projectRepository.findById(projectId);
            if (projectCandidate.isPresent())
                return projectCandidate.get().getStudent().getId().equals(possibleOwner.getUserId());
        }
        return false;
    }


    private Project parseProjectDescription(Project project) {
        project.setDescription(escapeCharacters(project.getDescription()));
        project.setDescription(addYoutubePlayer(project.getDescription(), ""));
        project.setDescription(addImagesTags(project.getDescription(), ""));
        project.setDescription(addOtherLinks(project.getDescription()));
        return project;
    }

    private String escapeCharacters(String string) {
        StringBuilder result = new StringBuilder();
        for (char character : string.toCharArray()) {
            switch (character) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '\"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(character);
            }
        }
        return result.toString();
    }

    private String addImagesTags(String string, String result) {
        Matcher matcher = IMAGE_PATTERN.matcher(string);
        if (matcher.find()) {
            String newString = string.substring(0, matcher.end()).replace(matcher.group(), "<br><img src=\"" + matcher.group() + "\"/><br>");
            return addImagesTags(string.substring(matcher.end()),
                    result.concat(newString));
        } else {
            if (string.equals("")) return result;
            else return result.concat(string);
        }
    }

    private String addYoutubePlayer(String string, String result) {
        Matcher matcher = YOUTUBE_PATTERN.matcher(string);
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
        Matcher matcher = Pattern.compile(COMMON_URL_REGEX, Pattern.DOTALL).matcher(string);
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
