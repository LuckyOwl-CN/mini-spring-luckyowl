package com.luckyowl.test.beans.factory.config;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的bean定义加载完成后，但在bean实例化之前，提供修改beanDefinition属性值的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
