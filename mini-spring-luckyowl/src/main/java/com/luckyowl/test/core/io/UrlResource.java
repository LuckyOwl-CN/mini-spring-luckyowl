package com.luckyowl.test.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @description url资源
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection connection = url.openConnection();
        try{
            return connection.getInputStream();
        }catch (IOException e){
            throw e;
        }
    }
}
