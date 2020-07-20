package ru.itis.practice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.practice.models.User;
import ru.itis.practice.TestConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfiguration.class)
class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Autowired
    private TestEntityManager entityManager;
    private MultipartFile file;

    @BeforeEach
    void init() {
        file = new MultipartFile() {
            @Override
            public String getName() {
                return "test.Name";
            }

            @Override
            public String getOriginalFilename() {
                return "test.OriginalName";
            }

            @Override
            public String getContentType() {
                return "testContentType";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {

            }
        };
    }

    @Test
    void testSaveOnNullShouldThrowException() {
        assertThrows(Throwable.class, () -> imageService.save(file, 1L));
    }

    @Test
    void testSaveFile() {
        String initialPath = getPhotoPath(3L);
        assertNotEquals(initialPath, imageService.save(file, 3L));
    }

    private String getPhotoPath(Long id) {
        return entityManager.getEntityManager()
                .createQuery("from User user where user.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult()
                .getPhotoPath();
    }

    @Test
    void testSavedFileHaveUniqueName() {
        List<String> usedPaths = entityManager.getEntityManager().
                createQuery("from User", User.class)
                .getResultList().stream()
                .map(User::getPhotoPath)
                .distinct()
                .collect(Collectors.toList());
        assertFalse(usedPaths.contains(imageService.save(file, 3L)));
    }

    @Test
    void testSaveMethodExceptionHandling() {
        MultipartFile file = (MultipartFile) Proxy.newProxyInstance(this.file.getClass().getClassLoader(),
                this.file.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    if (method.getName().equals("transferTo")) {
                        System.out.println("method called");
                        method.invoke(proxy, args);
                        throw new IOException();
                    }
                    return proxy;
                });
        assertThrows(RuntimeException.class, () -> imageService.save(file, 3L));
    }
}