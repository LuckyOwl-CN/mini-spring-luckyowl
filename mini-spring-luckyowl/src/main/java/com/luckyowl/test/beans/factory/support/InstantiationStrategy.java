package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.config.BeanDefinition;

/**
 * @description 实例化策略
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public interface InstantiationStrategy {

    /**
     * 传入bean定义，根据不同实现策略来实例化对象
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    Object instantaite(BeanDefinition beanDefinition) throws BeansException;

}
