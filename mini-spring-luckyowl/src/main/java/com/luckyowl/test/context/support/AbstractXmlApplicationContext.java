package com.luckyowl.test.context.support;

import com.luckyowl.test.beans.factory.support.DefaultListableBeanFactory;
import com.luckyowl.test.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory){
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
