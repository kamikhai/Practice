package ru.itis.practice.services.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import ru.itis.practice.repositories.*;
import ru.itis.practice.services.*;

@TestConfiguration
@TestPropertySource("classpath:values.properties")
public class CommonConfiguration {

    @Bean(name = "testCompetenceService")
    public CompetenceService competenceService(CompetenceRepository cR,
                                               @Qualifier("testTagService") TagService tS,
                                               @Qualifier("testStudentService") StudentService sS) {
        return new CompetenceServiceImpl(cR, tS, sS);
    }

    @Bean(name = "testExcelService")
    public ExcelService excelService(@Qualifier("testUserService") UserService uS,
                                     @Qualifier("testGroupService") GroupService gS,
                                     @Qualifier("testStudentService") StudentService sS,
                                     @Value("${storage.path}") String path) {
        return new ExcelServiceImpl(uS, gS, sS, path);
    }

    @Bean(name = "testGroupService")
    public GroupService groupService(GroupRepository gR) {
        return new GroupServiceImpl(gR);
    }

    @Bean(name = "testImageService")
    public ImageService imageService(@Qualifier("testUserService") UserService uS,
                                     @Value("${storage.path}") String path,
                                     @Value("${project.url}") String url) {
        return new ImageServiceImpl(uS, path, url);
    }

    @Bean(name = "testJobProfileService")
    public JobProfileService jobProfileService(JobProfileRepository pR) {
        return new JobProfileServiceImpl(pR);
    }

    @Bean(name = "testProjectService")
    public ProjectService projectService(ProjectRepository pR,
                                         StudentRepository sR,
                                         TagRepository tR) {
        return new ProjectServiceImpl(pR, sR, tR);
    }

    @Bean(name = "testStudentService")
    public StudentService studentService(StudentRepository sR,
                                         CompetenceRepository cR) {
        return new StudentServiceImpl(sR, cR);
    }

    @Bean(name = "testTeacherService")
    public TeacherService teacherService(TeacherRepository tR) {
        return new TeacherServiceImpl(tR);

    }

    @Bean(name = "testTokenService")
    public TokenService tokenService() {
        return new TokenServiceImpl();

    }

    @Bean(name = "testUserService")
    public UserService userService(UserRepository uR,
                                   PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(uR, passwordEncoder);
    }

    @Bean(name = "testTagService")
    public TagService tagService(TagRepository tagRepository) {
        return new TagServiceImpl(tagRepository);
    }
}
