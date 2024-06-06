package com.luckyowl.test.beans.factory;

import com.luckyowl.test.beans.PropertyValue;
import com.luckyowl.test.beans.PropertyValues;
import com.luckyowl.test.beans.factory.config.BeanDefinition;
import com.luckyowl.test.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class PopulateBeanWithPropertyValuesTest {

    @Test
    public void test() throws Exception{
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "luckyowl"));
        propertyValues.addPropertyValue(new PropertyValue("message", "hello"));
        propertyValues.addPropertyValue(new PropertyValue("code", 100));
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class, propertyValues);
        defaultListableBeanFactory.registerBeanDefinition("helloService", beanDefinition);

        HelloService helloService = (HelloService) defaultListableBeanFactory.getBean("helloService");
        assertThat(helloService.getName()).isEqualTo("luckyowl");
        assertThat(helloService.getMessage()).isEqualTo("hello");
        assertThat(helloService.getCode()).isEqualTo(100L);
    }
}
