package com.luckyowl.test.context.event;

import com.luckyowl.test.context.ApplicationContext;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/14
 **/
public class ContextClosedEvent extends ApplicationContextEvent {

    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
