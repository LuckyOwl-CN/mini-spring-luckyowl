package com.luckyowl.test.ioc;

import com.luckyowl.test.service.HelloService;
import com.luckyowl.test.beans.PropertyValue;
import com.luckyowl.test.beans.PropertyValues;
import com.luckyowl.test.beans.factory.config.BeanDefinition;
import com.luckyowl.test.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/5
 **/
public class BeanFactoryTest {

    @Test
    public void testBeanFactory() throws Exception{

        // 创建一个bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建bean属性列表
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("message","hello"));
        propertyValues.addPropertyValue(new PropertyValue("name","luckyowl"));
        // 注册一个bean定义、携带属性列表生成bean定义
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class, propertyValues);
        // 通过bean工厂根据beanName获取bean实例
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        //测试通过beanName获取到的bean实例能否正常使用
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        assertThat(helloService).isNotNull();
        assertThat(helloService.sayHello()).isEqualTo("hello");

        //获取属性值
        assertThat(helloService.getName()).isEqualTo("luckyowl");
        assertThat(helloService.getMessage()).isEqualTo("hello");
    }
}
