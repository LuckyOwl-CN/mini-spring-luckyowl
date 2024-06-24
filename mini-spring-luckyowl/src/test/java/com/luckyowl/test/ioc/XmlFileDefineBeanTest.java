package com.luckyowl.test.ioc;

import com.luckyowl.test.bean.Car;
import com.luckyowl.test.bean.Person;
import com.luckyowl.test.beans.factory.support.DefaultListableBeanFactory;
import com.luckyowl.test.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/7
 **/
public class XmlFileDefineBeanTest {

    @Test
    public void testXmlFile() throws Exception{
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        assertThat(person.getName()).isEqualTo("luckyowl");
        assertThat(person.getCar().getBrand()).isEqualTo("su7");

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
        assertThat(car.getBrand()).isEqualTo("su7");
    }
}
