package com.luckyowl.test.ioc.common;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.bean.Car;
import com.luckyowl.test.beans.factory.config.BeanPostProcessor;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public class CustomerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor#postProcessBeforeInitialization");
        if("car".equals(beanName)){
            ((Car) bean).setBrand("lamborghini");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor#postProcessAfterInitialization");
        return bean;
    }
}
