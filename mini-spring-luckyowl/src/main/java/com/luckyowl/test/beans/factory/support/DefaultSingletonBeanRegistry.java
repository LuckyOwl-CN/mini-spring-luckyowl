package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 默认单例bean注册表——实现单例bean注册表接口
 *
 * @author yuanjiahao03
 * @date 2024/6/6
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存储所有单例bean的Map
     */
    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 注册单例bean
     * @param beanName
     * @param singletonObject
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
