package com.luckyowl.test.ioc;

import com.luckyowl.test.beans.bean.Car;
import com.luckyowl.test.beans.bean.Person;
import com.luckyowl.test.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public class ApplicationContextTest {

    @Test
    public void testApplicationContext() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        //name属性在CustomBeanFactoryPostProcessor中被修改为owl
        assertThat(person.getName()).isEqualTo("unknown");

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);
        assertThat(car.getBrand()).isEqualTo("lamborghini");
    }
}
