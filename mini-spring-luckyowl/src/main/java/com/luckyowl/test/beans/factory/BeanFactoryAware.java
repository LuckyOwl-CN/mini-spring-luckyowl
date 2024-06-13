package com.luckyowl.test.beans.factory;

import com.luckyowl.test.beans.BeansException;

/**
 * @description 实现该接口，能感知所属BeanFactory
 *
 * @author LuckyOwl-CN
 * @date 2024/6/13
 **/
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
