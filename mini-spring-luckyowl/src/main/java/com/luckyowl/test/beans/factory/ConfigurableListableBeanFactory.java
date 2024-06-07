package com.luckyowl.test.beans.factory;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.config.BeanDefinition;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/7
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory {

    /**
     * 根据bean名称获取bean定义
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 预实例化所有单例bean
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;
}
