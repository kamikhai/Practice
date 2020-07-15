package ru.itis.practice.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.practice.models.Group;
import ru.itis.practice.models.Student;
import ru.itis.practice.models.User;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.UUID;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    @Value("${storage.path}")
    String path;

    @Override
    public File add(MultipartFile file, String group) {

        if (!file.isEmpty()) {

            try {

                byte[] fileBytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                System.out.println("File original name: " + file.getOriginalFilename());

                File newFile = new File(path + File.separator + file.getName() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")) + ".xlsx");

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(fileBytes);
                stream.close();
                addPassword(newFile, group);

                System.out.println("Файл сохранен: " + path + File.separator + file.getName() + LocalDateTime.now() + ".xlsx");
                return newFile;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    private void addPassword(File newFile, String group) throws IOException {

        FileInputStream inputStream = new FileInputStream(newFile);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        Group g = groupService.saveIfNotExist(Group.builder()
                .numeric(group)
                .build());

        int i = 0;

        while (rowIterator.hasNext()) {
            rowIterator.next();

            if (i != 0) {
                XSSFCell cell = sheet.getRow(i).createCell(2);
                cell.setCellValue(UUID.randomUUID().toString());

                User user = userService.save(User.builder()
                        .fullName(sheet.getRow(i).getCell(0)
                                .getStringCellValue())
                        .role(User.Role.STUDENT)
                        .email(sheet.getRow(i).getCell(1).getStringCellValue())
                        .passHash(sheet.getRow(i).getCell(2).getStringCellValue())
                        .photoPath("img/empty_user.jpg")
                        .build());

                Student s = studentService.save(Student.builder()
                        .group(g)
                        .user(user)
                        .description("Нет информации")
                        .build());

            } else {
                XSSFCell cell = sheet.getRow(i).createCell(2);
                cell.setCellValue("Пароль");
            }
            i++;
        }
        inputStream.close();
        FileOutputStream out = new FileOutputStream(newFile);
        workbook.write(out);
        out.close();
    }
}
