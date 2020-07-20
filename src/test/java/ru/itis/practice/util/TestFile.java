package ru.itis.practice.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

public class TestFile implements MultipartFile {

    @Override
    public String getName() {
        return "test.ext";
    }

    @Override
    public String getOriginalFilename() {
        return "test.ext";
    }

    @Override
    public String getContentType() {
        return "test/test";
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
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    @Override
    public void transferTo(File file) {}
}
