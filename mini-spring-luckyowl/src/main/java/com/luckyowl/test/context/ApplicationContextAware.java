package com.luckyowl.test.context;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.Aware;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/13
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
