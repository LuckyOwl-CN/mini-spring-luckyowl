package com.luckyowl.test.beans;

import com.luckyowl.test.beans.bean.Person;
import com.luckyowl.test.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/12
 **/
public class InitAndDestroyMethodTest {

    @Test
    public void testInitAndDestroyMethod() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.registerShutdownHook();
    }
}
