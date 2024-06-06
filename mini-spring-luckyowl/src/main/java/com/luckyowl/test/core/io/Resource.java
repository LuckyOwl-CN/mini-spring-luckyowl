package com.luckyowl.test.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @description 资源的抽象和访问接口
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
