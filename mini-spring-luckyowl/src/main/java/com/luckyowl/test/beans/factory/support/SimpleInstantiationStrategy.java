package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @description 简单的bean实例化策略
 * <p>
 *     通过bean的无参构造函数实例化对象
 * </p>
 *
 * @author yuanjiahao03
 * @date 2024/6/6
 **/
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    /**
     * 无参构造策略
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantaite(BeanDefinition beanDefinition) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try{
            // 通过反射获取bean的构造函数-传入参数为空则获取无参构造函数
            Constructor constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e){
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
