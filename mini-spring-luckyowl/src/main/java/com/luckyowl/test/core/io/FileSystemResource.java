package com.luckyowl.test.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * @description 文件系统下的资源
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class FileSystemResource implements Resource {

    private final String filePath;
    
    public FileSystemResource(String filePath){
        this.filePath = filePath;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        try{
            Path path = new File(this.filePath).toPath();
            return Files.newInputStream(path);
        } catch (NoSuchFileException e){
            throw new FileNotFoundException(e.getMessage());
        }
    }
}
