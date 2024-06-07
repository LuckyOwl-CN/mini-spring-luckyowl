package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.core.io.Resource;
import com.luckyowl.test.core.io.ResourceLoader;

/**
 * @description bean定义读取器抽象接口
 * <p>
 *     前置：通过资源加载器加载文件资源
 *     该Reader用于解析显示声明bean的文件，并把beanDefinition注册到beanDefinitionMap中
 * </p>
 *
 * @author LuckyOwl-CN
 * @date 2024/6/7
 **/
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 批量加载bean定义
     * @param locations
     * @throws BeansException
     */
    void loadBeanDefinitions(String[] locations) throws BeansException;
}
