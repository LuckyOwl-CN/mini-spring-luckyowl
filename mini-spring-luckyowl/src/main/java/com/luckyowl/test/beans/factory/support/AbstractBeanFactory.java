package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.BeanFactory;
import com.luckyowl.test.beans.factory.config.BeanDefinition;

/**
 * @description 抽象BeanFactory
 * <p>
 *     实现了-通过根据bean名称获取bean
 *     需要实现的方法：
 *     1.创建bean实例
 *     2.根据beanName获取bean定义
 * </p>
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 通过bean名称获取bean实例
     * @param name
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if(bean != null){
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    /**
     * 定义创建bean实例的方法
     * @param beanName
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    /**
     * 定义获取bean定义的方法
     * @param beanName
     * @return
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
