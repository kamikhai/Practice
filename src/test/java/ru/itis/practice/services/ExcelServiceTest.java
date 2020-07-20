package ru.itis.practice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.practice.util.TestFile;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class ExcelServiceTest {

    @Autowired
    private ExcelService excelService;

    @Test
    void testAddFile() {
        assertNull(excelService.add(new TestFile(), "11-802"));
    }
}