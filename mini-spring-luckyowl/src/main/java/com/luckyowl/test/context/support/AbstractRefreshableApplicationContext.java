package com.luckyowl.test.context.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.support.DefaultListableBeanFactory;

/**
 * @description 抽象可刷新上下文
 *
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 创建Bean工厂
     * @return
     */
    protected DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载Bean定义
     * @param beanFactory
     * @throws BeansException
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;

    public DefaultListableBeanFactory getBeanFactory(){
        return beanFactory;
    }
}
