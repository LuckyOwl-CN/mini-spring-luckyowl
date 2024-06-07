package com.luckyowl.test.beans.factory;

import com.luckyowl.test.beans.BeansException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/5
 **/
public interface BeanFactory {

    /**
     * 获取bean
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据名称和类型获取bean
     * @param name
     * @param requiredType
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
