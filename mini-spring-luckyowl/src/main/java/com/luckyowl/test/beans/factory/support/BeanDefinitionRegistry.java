package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.factory.config.BeanDefinition;

/**
 * @description  BeanDefinition注册表接口：用于注册BeanDefinition——定义注册beanDefinition的方法
 *
 * @author yuanjiahao03
 * @date 2024/6/6
 **/
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
