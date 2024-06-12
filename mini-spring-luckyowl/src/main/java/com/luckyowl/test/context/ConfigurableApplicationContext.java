package com.luckyowl.test.context;

import com.luckyowl.test.beans.BeansException;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
