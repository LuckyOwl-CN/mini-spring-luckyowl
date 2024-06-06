package com.luckyowl.test.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @description classPath下的资源
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class ClassPathResource implements Resource {

    private final String path;

    public ClassPathResource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        if(inputStream == null){
            throw new FileNotFoundException("路径[" + path + "]不存在，无法加载为输入流");
        }
        return inputStream;
    }

}
