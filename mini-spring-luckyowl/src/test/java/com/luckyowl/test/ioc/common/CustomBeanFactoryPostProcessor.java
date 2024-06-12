package com.luckyowl.test.ioc.common;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.PropertyValue;
import com.luckyowl.test.beans.PropertyValues;
import com.luckyowl.test.beans.factory.ConfigurableListableBeanFactory;
import com.luckyowl.test.beans.factory.config.BeanDefinition;
import com.luckyowl.test.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("CustomBeanFactoryPostProcessor#postProcessBeanFactory");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        //将person的属性值设置为unknown
        propertyValues.addPropertyValue(new PropertyValue("name", "unknown"));
    }
}
