package ru.itis.practice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.practice.models.User;
import ru.itis.practice.util.TestFile;

import java.io.IOException;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Autowired
    private TestEntityManager entityManager;

    private MultipartFile file;

    @BeforeEach
    void init() {
        file = new TestFile();
    }

    @Test
    void testSaveFile() {
        String initialPath = entityManager.getEntityManager()
                .createQuery("from User user where user.id=:id", User.class)
                .setParameter("id", 1L)
                .getSingleResult()
                .getPhotoPath();

        assertNotEquals(initialPath, imageService.save(file, 1L));
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