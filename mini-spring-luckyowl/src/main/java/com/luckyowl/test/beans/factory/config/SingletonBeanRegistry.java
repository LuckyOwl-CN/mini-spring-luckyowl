package com.luckyowl.test.beans.factory.config;

/**
 * @description  单例bean注册表
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public interface SingletonBeanRegistry {

    /**
     * 获取单例bean
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    /**
     * 注册单例bean
     * @param beanName
     * @param singletonObject
     */
    void addSingleton(String beanName, Object singletonObject);

}
