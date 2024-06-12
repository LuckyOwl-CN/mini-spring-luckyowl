package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.ConfigurableListableBeanFactory;
import com.luckyowl.test.beans.factory.config.BeanDefinition;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

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
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null){
            throw new BeansException("beanDefinitionMap中获取不到，beanName为[" + beanName + "]的bean定义");
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            //判断beanClass是否可以赋值给type，即beanClass是否是type的子类或实现了type的接口
            if(type.isAssignableFrom(beanClass)){
                T bean = (T) getBean(beanName);
                result.put(beanName, bean);
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        return beanNames.toArray(new String[beanNames.size()]);
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }
}
