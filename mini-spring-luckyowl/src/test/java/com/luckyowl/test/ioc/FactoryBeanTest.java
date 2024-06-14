package com.luckyowl.test.ioc;

import com.luckyowl.test.beans.bean.Car;
import com.luckyowl.test.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
  * @author LuckyOwl-CN
  * @date 2024/6/14
  **/
public class FactoryBeanTest {

    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = applicationContext.getBean("car", Car.class);
        assertThat(car.getBrand()).isEqualTo("porsche");
    }
}
