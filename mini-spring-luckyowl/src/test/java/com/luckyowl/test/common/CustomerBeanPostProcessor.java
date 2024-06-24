package com.luckyowl.test.common;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.bean.Car;
import com.luckyowl.test.beans.factory.config.BeanPostProcessor;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public class CustomerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor#postProcessBeforeInitialization, beanName: " + beanName);
        if("car".equals(beanName)){
            ((Car) bean).setBrand("lamborghini");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor#postProcessAfterInitialization, beanName: " + beanName);
        return bean;
    }
}
