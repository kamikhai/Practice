package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.practice.models.User;

import java.io.File;
import java.io.IOException;

@Component
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private UserService userService;
    @Value("${storage.path}")
    private String path;
    @Value("${project.url}")
    private String url;
    private final int length = 40;

    @Override
    public String save(MultipartFile file, Long id) {
        String type = "." + file.getOriginalFilename().split("\\.")[1];
        Long size = file.getSize();
        String name = file.getOriginalFilename().split("\\.")[0];
        String dbName = nameGenerate();
        try {
            file.transferTo(new File(path + File.separator + dbName + type));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        User user = userService.getUserById(id);
        user.setPhotoPath("http://" + url + "/profile/photo/" + dbName + type);
        userService.update(user);
        return dbName + type;
    }

    @Override
    public Resource get(String fileName) {
        File file = fileFor(fileName);
        Resource fileSystemResource = new FileSystemResource(file);
        return fileSystemResource;
    }

    private String nameGenerate() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    private File fileFor(String fileName) {
        return new File(path, fileName);
    }
}
