package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.practice.services.config.CommonConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = CommonConfiguration.class)
class ExcelServiceTest {

    @Autowired
    private ExcelService excelService;


    @Test
    void testAddFile() {
        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return "file.name";
            }

            @Override
            public String getOriginalFilename() {
                return "file.originalName";
            }

            @Override
            public String getContentType() {
                return "contentType";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 1234;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new InputStream() {
                    @Override
                    public int read() throws IOException {
                        return 0;
                    }
                };
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        assertNull(excelService.add(file, "11-802"));
    }
}