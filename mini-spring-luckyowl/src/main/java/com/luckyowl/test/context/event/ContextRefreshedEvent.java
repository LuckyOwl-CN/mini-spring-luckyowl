package com.luckyowl.test.context.event;

import com.luckyowl.test.context.ApplicationContext;

/**
 * @author yuanjiahao03
 * @date 2024/6/14
 **/
public class ContextRefreshedEvent extends ApplicationContextEvent {

    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
