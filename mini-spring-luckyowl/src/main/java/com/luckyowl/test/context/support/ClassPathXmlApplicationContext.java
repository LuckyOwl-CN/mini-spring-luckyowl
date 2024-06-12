package com.luckyowl.test.context.support;

import com.luckyowl.test.beans.BeansException;

/**
 * @description xml文件的应用上下文
 *
 *
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext(String configLocation) throws BeansException{
        this(new String[]{configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException{
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
