package com.luckyowl.test.beans.factory.config;

import com.luckyowl.test.beans.factory.HierarchicalBeanFactory;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/7
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
