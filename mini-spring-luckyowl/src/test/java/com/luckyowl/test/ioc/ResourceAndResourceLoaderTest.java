package com.luckyowl.test.ioc;

import cn.hutool.core.io.IoUtil;
import com.luckyowl.test.core.io.DefaultResourceLoader;
import com.luckyowl.test.core.io.Resource;
import com.luckyowl.test.core.io.UrlResource;
import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class ResourceAndResourceLoaderTest {

    @Test
    public void testResourceLoader() throws Exception{
        //创建默认资源加载器
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        //加载classpath下资源
        Resource resourceClz = resourceLoader.getResource("classpath:hello.txt");
        InputStream inputStreamClz = resourceClz.getInputStream();
        String contentClz = IoUtil.readUtf8(inputStreamClz);
        System.out.println(contentClz);
        assertThat(contentClz).isEqualTo("hello world, luckyowl!");

        //加载文件系统资源
        Resource resourceFile = resourceLoader.getResource("src/test/resources/hello.txt");
        InputStream inputStreamFile = resourceFile.getInputStream();
        String contentFile = IoUtil.readUtf8(inputStreamFile);
        System.out.println(contentFile);
        assertThat(contentFile).isEqualTo("hello world, luckyowl!");

        //加载url资源
        Resource resourceUrl = resourceLoader.getResource("https://github.com/LuckyOwl-CN/mini-spring-luckyowl/blob/main/README.md");
        assertThat(resourceUrl instanceof UrlResource).isTrue();
        InputStream inputStreamUrl = resourceUrl.getInputStream();
        String contentUrl = IoUtil.readUtf8(inputStreamUrl);
        System.out.println(contentUrl);
    }
}
