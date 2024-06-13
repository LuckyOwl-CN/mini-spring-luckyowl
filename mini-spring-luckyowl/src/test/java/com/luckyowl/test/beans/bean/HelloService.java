package com.luckyowl.test.beans.bean;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.BeanFactory;
import com.luckyowl.test.beans.factory.BeanFactoryAware;
import com.luckyowl.test.context.ApplicationContext;
import com.luckyowl.test.context.ApplicationContextAware;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class HelloService implements ApplicationContextAware, BeanFactoryAware {

    private String name;

    private String message;

    private Long code;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return this.code;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
