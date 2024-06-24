package com.luckyowl.test.context.event;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.BeanFactory;
import com.luckyowl.test.beans.factory.BeanFactoryAware;
import com.luckyowl.test.context.ApplicationEvent;
import com.luckyowl.test.context.ApplicationListener;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yuanjiahao03
 * @date 2024/6/14
 **/
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster , BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }
}
