package com.luckyowl.test.context.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.config.BeanPostProcessor;
import com.luckyowl.test.context.ApplicationContext;
import com.luckyowl.test.context.ApplicationContextAware;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/13
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
