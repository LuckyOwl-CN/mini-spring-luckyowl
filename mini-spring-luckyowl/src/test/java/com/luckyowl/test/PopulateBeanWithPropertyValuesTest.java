package com.luckyowl.test;

import com.luckyowl.test.beans.bean.Car;
import com.luckyowl.test.beans.bean.HelloService;
import com.luckyowl.test.beans.PropertyValue;
import com.luckyowl.test.beans.PropertyValues;
import com.luckyowl.test.beans.bean.Person;
import com.luckyowl.test.beans.factory.config.BeanDefinition;
import com.luckyowl.test.beans.factory.config.BeanReference;
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

    /**
     * bean中注入bean测试
     */
    @Test
    public void injectBeanTest(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册Car的bean对象
        PropertyValues carPropertyValues = new PropertyValues();
        carPropertyValues.addPropertyValue(new PropertyValue("brand", "奔驰"));
        BeanDefinition beanDefinition = new BeanDefinition(Car.class, carPropertyValues);
        beanFactory.registerBeanDefinition("car", beanDefinition);

        //注册Person的bean对象
        PropertyValues personPropertyValues = new PropertyValues();
        personPropertyValues.addPropertyValue(new PropertyValue("name", "张三"));
        personPropertyValues.addPropertyValue(new PropertyValue("age", 28));
        //Person的car属性使用bean引用
        personPropertyValues.addPropertyValue(new PropertyValue("car", new BeanReference("car")));
        BeanDefinition personBeanDefinition = new BeanDefinition(Person.class, personPropertyValues);
        beanFactory.registerBeanDefinition("person", personBeanDefinition);

        //获取person实例
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);

        //校验bean：person中的属性是否注入
        assertThat(person.getName()).isEqualTo("张三");
        assertThat(person.getAge()).isEqualTo(28);
        assertThat(person.getCar()).isNotNull();
        assertThat(person.getCar().getBrand()).isEqualTo("奔驰");
        System.out.println(person.getCar());
    }
}
