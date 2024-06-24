package com.luckyowl.test.ioc;

import com.luckyowl.test.service.HelloService;
import com.luckyowl.test.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/13
 **/
public class AwareInterfaceTest {

    @Test
    public void test() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        assertThat(helloService.getApplicationContext()).isNotNull();
        assertThat(helloService.getBeanFactory()).isNotNull();
    }
}
