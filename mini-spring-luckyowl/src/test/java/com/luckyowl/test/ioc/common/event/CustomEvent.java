package com.luckyowl.test.ioc.common.event;

import com.luckyowl.test.context.ApplicationContext;
import com.luckyowl.test.context.event.ApplicationContextEvent;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/14
 **/
public class CustomEvent extends ApplicationContextEvent {

    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
