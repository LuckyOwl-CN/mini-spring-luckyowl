package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 注册BeanDefinition到bean定义map中
     * @param beanName
     * @param beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        //同名bean（beanName相同）注册bean定义时，会更新bean定义为最新注册的bean定义
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null){
            throw new BeansException("beanDefinitionMap中获取不到，beanName为[" + beanName + "]的bean定义");
        }
        return beanDefinition;
    }
}
