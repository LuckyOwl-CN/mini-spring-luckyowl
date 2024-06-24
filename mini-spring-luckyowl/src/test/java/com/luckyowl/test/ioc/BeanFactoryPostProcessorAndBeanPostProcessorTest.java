package com.luckyowl.test.ioc;

import com.luckyowl.test.bean.Car;
import com.luckyowl.test.bean.Person;
import com.luckyowl.test.beans.factory.support.DefaultListableBeanFactory;
import com.luckyowl.test.beans.factory.xml.XmlBeanDefinitionReader;
import com.luckyowl.test.common.CustomBeanFactoryPostProcessor;
import com.luckyowl.test.common.CustomerBeanPostProcessor;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public class BeanFactoryPostProcessorAndBeanPostProcessorTest {

    @Test
    public void testBeanFactoryProcessor() throws Exception{
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);

        assertThat(person.getName()).isEqualTo("unknown");
    }

    @Test
    public void testBeanPostProcessor() throws Exception{
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        //添加bean实例化后的处理器
        CustomerBeanPostProcessor customerBeanPostProcessor = new CustomerBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customerBeanPostProcessor);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);

        assertThat(car.getBrand()).isEqualTo("lamborghini");
    }
}
