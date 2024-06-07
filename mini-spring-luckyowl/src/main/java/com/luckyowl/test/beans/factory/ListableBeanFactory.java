package com.luckyowl.test.beans.factory;

import com.luckyowl.test.beans.BeansException;

import java.util.Map;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/7
 **/
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 获取指定类型的所有bean实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 获取所有bean定义名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
